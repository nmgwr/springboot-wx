package com.nmgwr.admin.common.config;

import com.nmgwr.admin.common.exception.UserNotExistException;
import com.nmgwr.admin.modules.entity.SysMenu;
import com.nmgwr.admin.modules.entity.SysRole;
import com.nmgwr.admin.modules.entity.User;
import com.nmgwr.admin.modules.services.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.beetl.sql.core.SQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WxShiroRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;
    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    //session缓存类型
    @Value("${spring.session-type}")
    private String sessionType;

    @Autowired
    private SQLManager sqlManager;

    /**
     * 授权验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权验证");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        if(sessionType != null && sessionType.equals("redis")){
            user = (User)redisTemplate.opsForValue().get("shiro-session-user:" + subject.getSession().getId().toString());
        }else {
            user = (User)subject.getSession().getAttribute("userInfo");
        }
        if (user == null){
            return null;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("userId",user.getId());
        List<SysMenu> menuList = sqlManager.select("login.queryUserMenus",SysMenu.class,params);
        List<SysRole> roleList = sqlManager.select("login.queryUserRoles",SysRole.class,params);
        //我TM也不知道这块为啥从session里或者redis里取出来的用户有问题，估计是beetl的orm查询存在BUG，但是LoginController里的user信息（角色、菜单）是完整的。
//        List<SysMenu> menuList = user.getMenus();
//        List<SysRole> roleList = user.getRoles();
        //设置用户菜单权限
        if (menuList != null){
            for (SysMenu menu : menuList){
                if (!StringUtils.isEmpty(menu.getPermission())){
                    // 添加基于Permission的权限信息
                    String[] permissions = menu.getPermission().split(",");
                    for (String permission : permissions){
                        info.addStringPermission(permission);
                    }
                }
            }
        }
        // 添加用户角色信息
        if(roleList != null){
            for (SysRole role : roleList){
                info.addRole(role.getEnname());
            }
        }
        return info;
    }

    /**
     * 登陆验证
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        String loginName = (String) token.getPrincipal();
        Map params = new HashMap<String, String>();
        params.put("loginName",loginName);
        User user = loginService.getUser(params);
        if (user == null) {
            throw new UserNotExistException();
        }
        if (!user.getUserStatus().equals("0")) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                loginName, //用户名
                user.getPasswd(), //密码
                ByteSource.Util.bytes(loginName+"123!@#---$%6"),//自定义了md5加密的salt
                getName()  //realm name
        );
        user.setPasswd("你是看不到我滴！！！");
        Session session = SecurityUtils.getSubject().getSession();
        //验证通过后把用户信息放在缓存里（目前支持redis和默认的内存）
        if(sessionType != null && sessionType.equals("redis")){
            String redisKey = "shiro-session-user:" + session.getId().toString();
            redisTemplate.opsForValue().set(redisKey,user);
            redisTemplate.expire(redisKey, 1800, TimeUnit.SECONDS);//设置超时时间
        }else {
            session.setAttribute("userInfo",user);
        }

        return authenticationInfo;
    }

    /**
     * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
     */
    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            return null;
        }
        AuthorizationInfo info;
        Subject subject = SecurityUtils.getSubject();
        info = (AuthorizationInfo)this.getCacheManager().getCache("shiro_auth_info").get(subject.getSession().getId());
        if (info == null) {
            info = doGetAuthorizationInfo(principals);
            if (info != null) {
                this.getCacheManager().getCache("shiro_auth_info").put(subject.getSession().getId(),info);
            }
        }
        return info;
    }

}

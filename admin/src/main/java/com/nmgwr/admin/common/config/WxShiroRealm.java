package com.nmgwr.admin.common.config;

import com.nmgwr.admin.common.exception.UserNotExistException;
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
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class WxShiroRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    /**
     * 授权验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权验证");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
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
        //验证通过后把用户信息放在session里
        SecurityUtils.getSubject().getSession().setAttribute("userInfo",user);
        return authenticationInfo;
    }

}

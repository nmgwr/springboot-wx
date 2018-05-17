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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class WxShiroRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
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
                ByteSource.Util.bytes(loginName+"123!@#---$%6"),//salt=username+salt
                getName()  //realm name
        );
        user.setPasswd("你是看不到我滴！！！");
        //验证通过后把用户信息放在session里
        SecurityUtils.getSubject().getSession().setAttribute("userInfo",user);
        return authenticationInfo;
    }

}

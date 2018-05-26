package com.nmgwr.admin.common.config.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nmgwr.admin.common.config.ErrorEnum;
import com.nmgwr.admin.common.config.ResultUtil;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写了shiro的authc操作方法、主要是未登录返回json、不走原来的重定向了
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{

    //session缓存类型   这俩属性在该类中注入不了，在shiroconfig中通过构造初始化
    private String sessionType;
    //    redis操作工具
    public RedisTemplate<String, Object> redisTemplate;

    public FormAuthenticationFilter(String sessionType,RedisTemplate<String, Object> redisTemplate){
        this.sessionType = sessionType;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        Subject subject = super.getSubject(request, response);
//        if(StringUtils.isEmpty(subject.getPrincipals())){
//            subject = SecurityUtils.getSecurityManager().createSubject();
//        }
        return subject;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
           return loginError(response);
        }
    }

    public boolean loginError(ServletResponse response)throws Exception{
        HttpServletResponse resp = (HttpServletResponse)response;
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "application/json;charset=UTF-8");
        //转json输出null值
        resp.getWriter().print(JSON.toJSONString(ResultUtil.error(ErrorEnum.NO_LOGIN),SerializerFeature.WriteMapNullValue));
        return false;
    }
}

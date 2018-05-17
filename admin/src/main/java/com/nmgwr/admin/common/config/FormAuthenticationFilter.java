package com.nmgwr.admin.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写了shiro的authc操作方法、主要是未登录返回json、不走原来的重定向了
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-type", "application/json;charset=UTF-8");
            //转json输出null值
            resp.getWriter().print(JSON.toJSONString(ResultUtil.error(ErrorEnum.NO_LOGIN),SerializerFeature.WriteMapNullValue));
            return false;
        }
    }

}

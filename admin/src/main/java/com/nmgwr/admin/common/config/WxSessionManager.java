package com.nmgwr.admin.common.config;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义sessionId获取
 */
public class WxSessionManager extends DefaultWebSessionManager {

    public WxSessionManager(){
        super();
    }

    /**
     * //设置请求头中的 Authorization 值为sessionId
     * @param request
     * @param response
     * @return
     */

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
        return id;
    }

}

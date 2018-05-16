package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.services.IntfService;
import com.nmgwr.admin.modules.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;
    @Autowired
    private IntfService intfService;

    @RequestMapping("/sys/login")
    public Result login(@RequestParam Map<String,String> params, HttpServletRequest req){
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if(user != null){
            return ResultUtil.success("用户【" + user + "】已登陆:" + session.getId());
        }else {
            return ResultUtil.success("用户未登陆:" + session.getId());
        }
    }

    @RequestMapping("putsession")
    public Result putsession(HttpServletRequest req){
        HttpSession session = req.getSession();
        log.info(session.getClass().toString());
        log.info(session.getId());
        session.setAttribute("user","温馨");
        return ResultUtil.success();
    }

}

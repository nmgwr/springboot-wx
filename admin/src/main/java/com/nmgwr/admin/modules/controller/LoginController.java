package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.services.IntfService;
import com.nmgwr.admin.modules.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private IntfService intfService;

    @RequestMapping("/sys/login")
    public Result login(@RequestParam Map<String,String> params, HttpServletRequest req){
        Result resp = new Result();
        return resp;
    }

}

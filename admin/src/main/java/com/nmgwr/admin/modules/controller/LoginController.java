package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.modules.entity.RespEntity;
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
    public RespEntity login(@RequestParam Map<String,String> params, HttpServletRequest req){
        RespEntity checkResult = intfService.chechData(params);
        if(checkResult.getCode().equals("0000")){
            return loginService.Login(params,req);
        }else {
            return checkResult;
        }
    }

}

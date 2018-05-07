package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.modules.entity.SysUser;
import com.nmgwr.admin.modules.services.UserService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/sys/user/findPage")
    public PageQuery<SysUser> findPage(SysUser user){
        return userService.findPage(user);
    }

}

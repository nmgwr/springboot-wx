package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.entity.SysUser;
import com.nmgwr.admin.modules.services.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("user:user:view")
    @RequestMapping("/sys/user/findPage")
    public Result findPage(SysUser user){
        return ResultUtil.success(userService.findPage(user));
    }

}

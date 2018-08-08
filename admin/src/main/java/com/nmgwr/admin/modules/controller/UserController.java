package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.entity.SysUser;
import com.nmgwr.admin.modules.services.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController注解能够使项目支持Rest
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     *分页查询用户
     * @param user
     * @return
     */
    @RequiresPermissions("user:user:view")
    @RequestMapping("/sys/user/findPage")
    public Result findPage(SysUser user){
        return ResultUtil.success(userService.findPage(user));
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping("/sys/user/saveUser")
    public Result save(SysUser user){ return ResultUtil.success(userService.saveUser(user)); }

    /**
     * 编辑用户
     * @param user
     * @return
     */
    @RequestMapping("/sys/user/updateUser")
    public Result update(SysUser user){ return ResultUtil.success(userService.updateUser(user)); }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @RequestMapping("/sys/user/deleteUser")
    public Result delete(SysUser user){ return ResultUtil.success(userService.deleteUser(user));}
}

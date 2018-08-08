package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.entity.SysRole;
import com.nmgwr.admin.modules.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;

    /**
     * 查看角色
     * @param role
     * @return
     */
    @RequestMapping("/sys/role/roleList")
    public  Result findList(SysRole role){
        return ResultUtil.success(roleService.findList(role));
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @RequestMapping("/sys/role/saveRole")
    public Result save(SysRole role){ return ResultUtil.success(roleService.saveRole(role)); }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @RequestMapping("/sys/role/updateRole")
    public Result update(SysRole role){ return ResultUtil.success(roleService.updateRole(role)); }

    /**
     * 删除角色
     * @param role
     * @return
     */
    @RequestMapping("/sys/role/deleteRole")
    public Result delete(SysRole role){ return ResultUtil.success(roleService.deleteRole(role));}

}

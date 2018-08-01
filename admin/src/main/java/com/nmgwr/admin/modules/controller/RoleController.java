package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.entity.SysRole;
import com.nmgwr.admin.modules.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;
    @RequestMapping("/sys/role/findList")
    public  Result findList(@RequestParam Map<String,String> params){
        return ResultUtil.success(roleService.findList(params));
    }
}

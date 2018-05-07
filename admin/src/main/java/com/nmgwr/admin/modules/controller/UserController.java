package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.modules.entity.SysUser;
import com.nmgwr.admin.modules.services.UserService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/sys/user/findPage")
    public PageQuery<SysUser> findPage(HttpServletRequest req){
        Map<String,String> params = new HashMap<String, String>();
        params.put("pageNum",req.getParameter("pageNum"));
        params.put("pageSize",req.getParameter("pageSize"));
        params.put("name",req.getParameter("name"));
        params.put("id",req.getParameter("id"));
        params.put("orderBy",req.getParameter("orderBy"));
        return userService.findPage(params);
    }

}

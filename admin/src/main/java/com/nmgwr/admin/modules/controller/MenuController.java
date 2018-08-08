package com.nmgwr.admin.modules.controller;

import com.nmgwr.admin.common.config.ResultUtil;
import com.nmgwr.admin.modules.entity.Result;
import com.nmgwr.admin.modules.entity.SysMenu;
import com.nmgwr.admin.modules.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.awt.SystemColor.menu;

@RestController
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表
     * @return
     */
    @RequestMapping("/sys/menu/menuList")
    public Result menuList(){ return ResultUtil.success(menuService.menuList()); }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @RequestMapping("/sys/menu/saveMenu")
    public Result save(SysMenu menu){ return ResultUtil.success(menuService.saveMenu(menu)); }

    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    @RequestMapping("/sys/menu/updateMenu")
    public Result update(SysMenu menu){ return ResultUtil.success(menuService.updateMenu(menu)); }

    /**
     * 删除菜单
     * @param menu
     * @return
     */
    @RequestMapping("/sys/menu/deleteMenu")
    public Result delete(SysMenu menu){ return ResultUtil.success(menuService.deleteMenu(menu));}
}

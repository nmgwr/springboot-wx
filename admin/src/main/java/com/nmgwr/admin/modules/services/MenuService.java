package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.MenuDao;
import com.nmgwr.admin.modules.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.awt.SystemColor.menu;

@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;
/*
查询所有菜单
* */
    public List<SysMenu> menuList(){
        return menuDao.menuList();
    }

/*
* 新增菜单*/
    public int saveMenu(SysMenu menu) { return  menuDao.saveMenu(menu); }

/*
*编辑菜单*/
    public int updateMenu(SysMenu menu){ return  menuDao.updateMenu(menu); }

/*
删除菜单*/
    public  int deleteMenu(SysMenu menu) { menuDao.deleteRoleMenu(menu);
        menuDao.deleteMenu(menu);
        return  menuDao.deleteMenu(menu);}

}

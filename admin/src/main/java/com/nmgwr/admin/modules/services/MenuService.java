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

    /**
     * 查看菜单列表
     * @return
     */
    public List<SysMenu> menuList(){
        return menuDao.menuList();
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    public int saveMenu(SysMenu menu) { return  menuDao.saveMenu(menu); }

    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    public int updateMenu(SysMenu menu){ return  menuDao.updateMenu(menu); }

    /**
     * 删除菜单
     * @param menu
     * @return
     */
    public  int deleteMenu(SysMenu menu) { menuDao.deleteRoleMenu(menu);
        menuDao.deleteMenu(menu);
        return  menuDao.deleteMenu(menu);}

}

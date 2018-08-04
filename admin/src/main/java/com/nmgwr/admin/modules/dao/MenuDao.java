package com.nmgwr.admin.modules.dao;

import com.nmgwr.admin.modules.entity.SysMenu;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("menu")
public interface MenuDao extends BaseMapper {

    List<SysMenu> menuList();

    int saveMenu(SysMenu menu);

    int updateMenu(SysMenu menu);

    int deleteMenu(SysMenu menu);

    int deleteRoleMenu(SysMenu menu);
}

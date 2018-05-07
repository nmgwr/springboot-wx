package com.nmgwr.admin.modules.dao;

import com.nmgwr.admin.modules.entity.SysUser;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

public interface UserDao extends BaseMapper<SysUser> {

    void findListPage(PageQuery<SysUser> query);

}

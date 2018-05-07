package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.common.utils.PageQuery;
import com.nmgwr.admin.modules.dao.UserDao;
import com.nmgwr.admin.modules.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    public PageQuery<SysUser> findPage(SysUser user){
        PageQuery pageQuery = new PageQuery(user);
        userdao.findListPage(pageQuery);
        return pageQuery;
    }

}

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

    /**
     * 分页查询用户
     * @param user
     * @return
     */
    public PageQuery<SysUser> findPage(SysUser user){
        PageQuery pageQuery = new PageQuery(user);
        userdao.findListPage(pageQuery);
        return pageQuery;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int saveUser(SysUser user) { return  userdao.saveUser(user); }

    /**
     * 编辑用户
     * @param user
     * @return
     */
    public int updateUser(SysUser user){ return  userdao.updateUser(user); }

    /**
     * 删除用户
     * @param user
     * @return
     */
    public  int deleteUser(SysUser user) { userdao.deleteUserRole(user);
        userdao.deleteUser(user);
        return  userdao.deleteUser(user);}

}

package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.RoleDao;
import com.nmgwr.admin.modules.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询角色
     * @param params
     * @return
     */
    public List<SysRole> findList(SysRole role){
        return roleDao.findList(role);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    public int saveRole(SysRole role) { return  roleDao.saveRole(role); }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    public int updateRole(SysRole role){ return  roleDao.updateRole(role); }

    /**
     * 删除角色
     * @param role
     * @return
     */
    public  int deleteRole(SysRole role) {
        roleDao.deleteRoleMenu(role);
        roleDao.deleteUserRole(role);
        roleDao.deleteRole(role);
        return  roleDao.deleteRole(role);}

}

package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.RoleDao;
import com.nmgwr.admin.modules.entity.SysRole;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleService {
    @Autowired
    private RoleDao roledao;
    /**
     * 根据参数查询角色
     * @param params
     * @return
     */
    public List findList(Map<String,String> params){
        return roledao.findList(params);
    }


}

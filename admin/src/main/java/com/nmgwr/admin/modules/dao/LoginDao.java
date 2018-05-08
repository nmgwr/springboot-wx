package com.nmgwr.admin.modules.dao;

import com.nmgwr.admin.modules.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.Map;

@SqlResource("login")
public interface LoginDao extends BaseMapper {

    User login(Map<String,String> params);

}

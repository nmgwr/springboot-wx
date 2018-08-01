package com.nmgwr.admin.modules.dao;

import com.nmgwr.admin.modules.entity.SysRole;
import com.nmgwr.admin.modules.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;
//@SqlResource注解就是在sql目录下对应的.md文件。如果是java.account则对应是sql目录下的java目录下的account.md文件。这个文件写有相关的对数据库的操作。
@SqlResource("role")
public interface RoleDao extends BaseMapper {

    List findList(Map<String,String> params);

}

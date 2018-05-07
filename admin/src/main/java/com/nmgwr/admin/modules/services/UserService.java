package com.nmgwr.admin.modules.services;

import com.nmgwr.admin.modules.dao.UserDao;
import com.nmgwr.admin.modules.entity.SysUser;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    public PageQuery<SysUser> findPage(Map<String,String> params){
        //分页对象，默认调用初始化页数，和页数大小的构造器
        PageQuery<SysUser> pageQuery = new PageQuery<SysUser>(Long.parseLong(params.get("pageNum")),Long.parseLong(params.get("pageSize")));
        //设置请求参数
        pageQuery.setParas(params);
        //设置orderBy排序
        pageQuery.setOrderBy(params.get("orderBy"));
        //查询后会自动把结果回写到pageQuery里。所以下边直接返回pageQuery对象即可
        userdao.findListPage(pageQuery);
        return pageQuery;
    }

}

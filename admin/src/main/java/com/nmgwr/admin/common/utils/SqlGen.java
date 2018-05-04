package com.nmgwr.admin.common.utils;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 根据数据库表生成实体类和sql文件工具类
 */
@Service
public class SqlGen {

    @Autowired
    SQLManager sqlManager;

}

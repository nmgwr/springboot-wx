package com.nmgwr.admin.common.utils;

import org.springframework.util.ReflectionUtils;

public class PageQuery<T> extends org.beetl.sql.core.engine.PageQuery {

    /**
     * 重写构造，通过spring的反射工具类初始化PageQuery
     * @param entity
     */
    public PageQuery(Object entity){
        this.setPageNumber((int)ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(entity.getClass(),"getPageNum"),entity));
        this.setPageSize((int)ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(entity.getClass(),"getPageSize"),entity));
        this.setParas(entity);
        this.setOrderBy((String)ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(entity.getClass(),"getOrderBy"),entity));
    }


}

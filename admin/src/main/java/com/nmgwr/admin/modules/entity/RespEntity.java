package com.nmgwr.admin.modules.entity;

import lombok.Data;

import java.util.Map;

/**
 * 外围系统返回报文定义
 */
@Data
public class RespEntity {

    private String code;
    private String desc;
    private Map<String,Object> data;

}

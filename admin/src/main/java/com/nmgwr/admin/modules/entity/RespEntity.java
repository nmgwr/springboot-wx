package com.nmgwr.admin.modules.entity;

import lombok.Data;

/**
 * 外围系统返回报文定义
 */
@Data
public class RespEntity {

    private String code;
    private String desc;
    private Object data;

    public RespEntity(){}

    public RespEntity(String code){
        this.setCode(code);
    }

    public RespEntity(String code,String desc){
        this.setCode(code);
        this.setDesc(desc);
    }

    public RespEntity(String code,String desc,Object data){
        this.setCode(code);
        this.setDesc(desc);
        this.setData(data);
    }

}

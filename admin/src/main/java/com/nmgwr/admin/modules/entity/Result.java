package com.nmgwr.admin.modules.entity;

import lombok.Data;

/**
 * 外围系统返回报文定义
 */
@Data
public class Result {

    private String code;
    private String desc;
    private Object data;

    public Result(){}

    public Result(String code){
        this.setCode(code);
    }

    public Result(String code, String desc){
        this.setCode(code);
        this.setDesc(desc);
    }

    public Result(String code, String desc, Object data){
        this.setCode(code);
        this.setDesc(desc);
        this.setData(data);
    }

}

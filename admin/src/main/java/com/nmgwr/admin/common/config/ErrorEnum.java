package com.nmgwr.admin.common.config;

public enum  ErrorEnum {

    UNKONW_ERROR("-1","未知错误"),
    USER_NOT_FIND("-101","用户不存在"),
    ERROR("-2","服务器错误"),
    PASSWD_ERROR("-102","密码错误");

    private String code;
    private String desc;

    ErrorEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

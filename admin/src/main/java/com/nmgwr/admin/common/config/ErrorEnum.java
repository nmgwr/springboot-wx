package com.nmgwr.admin.common.config;

public enum  ErrorEnum {

    UNKONW_ERROR("-1","未知错误"),
    ERROR("-2","页面不存在"),
    USER_NOT_FIND("-101","用户不存在"),
    USER_STATUS_ERROR("-102","用户被冻结"),
    NO_LOGIN("-999","未登陆或登陆超时，请求拒绝"),
    NO_AUTH("-888","权限不足"),
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

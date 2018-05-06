package com.nmgwr.admin.modules.entity;

import lombok.Data;

@Data
public class SysLog extends BaseEntity{

    private String type;
    private String title;
    private String requestUrl;
    private String remoteAddr;
    private String uesrAgent;
    private String method;
    private String params;
    private String exception;
}

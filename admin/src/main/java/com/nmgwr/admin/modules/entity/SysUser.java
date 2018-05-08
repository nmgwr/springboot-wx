package com.nmgwr.admin.modules.entity;

import lombok.Data;

@Data
public class SysUser extends BaseEntity {

    private String name;
    private String companyId;
    private String officeId;
    private String loginName;
    private String phone;
    private String email;
    private String mobile;
    private String userType;
    private String loginIp;
    private String loginDate;
    private String areaCode;
    private String userStatus;
}

package com.nmgwr.admin.modules.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysOffice {

    private String id;
    private String parentId;
    private String name;
    private String areaId;
    private String code;
    private String type;
    private String grade;
    private String address;
    private String phone;
    private String email;
    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date updateDate;
    private String remarks;
    private String delFlag;

}

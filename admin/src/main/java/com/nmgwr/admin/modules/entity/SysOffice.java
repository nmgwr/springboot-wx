package com.nmgwr.admin.modules.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysOffice extends BaseEntity {


    private String parentId;
    private String name;
    private String areaId;
    private String code;
    private String type;
    private String grade;
    private String address;
    private String phone;
    private String email;

}

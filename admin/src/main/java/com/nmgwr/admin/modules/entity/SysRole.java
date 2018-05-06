package com.nmgwr.admin.modules.entity;

import lombok.Data;

@Data
public class SysRole extends BaseEntity {

    private String name;
    private String enname;
    private String roleType;
}

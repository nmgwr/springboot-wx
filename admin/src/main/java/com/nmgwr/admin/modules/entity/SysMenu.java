package com.nmgwr.admin.modules.entity;


import lombok.Data;

@Data
public class SysMenu extends BaseEntity {

    private String parentId;
    private String name;
    private String href;
    private String icon;
    private String isShow;
    private String type;
    private String permission;

}

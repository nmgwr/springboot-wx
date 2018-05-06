package com.nmgwr.admin.modules.entity;

import lombok.Data;

@Data
public class SysArea extends BaseEntity{

    private String parentId;
    private String name;
    private String code;
    private String type;

}

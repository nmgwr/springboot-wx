package com.nmgwr.admin.modules.entity;


import lombok.Data;

@Data
public class SysDict extends BaseEntity {

    private String id;
    private String value;
    private String label;
    private String type;
    private String description;

}

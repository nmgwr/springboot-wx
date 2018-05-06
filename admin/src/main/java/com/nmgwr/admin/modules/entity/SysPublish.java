package com.nmgwr.admin.modules.entity;

import lombok.Data;

@Data
public class SysPublish extends BaseEntity {

    private String content;
    private String title;
    private String status;
    private String type;
    private String typeValue;

}

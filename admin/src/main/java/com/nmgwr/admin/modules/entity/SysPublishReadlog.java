package com.nmgwr.admin.modules.entity;

import lombok.Data;

@Data
public class SysPublishReadlog extends BaseEntity {

    private String publishId;
    private String readUser;
    private String readDate;
}

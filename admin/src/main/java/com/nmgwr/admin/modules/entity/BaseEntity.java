package com.nmgwr.admin.modules.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date updateDate;
    private String remarks;
    private String delFlag;
    private String id;
    private int pageSize;
    private int pageNum;
    private String orderBy;

}

package com.nmgwr.admin.modules.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date updateDate;
    private String remarks;
    private String delFlag = "0";
    private String id;
    private int pageSize;
    private int pageNum;
    private String orderBy;

}

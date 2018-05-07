package com.nmgwr.admin.modules.entity;

import lombok.Data;

import javax.management.relation.Role;
import java.util.List;

@Data
public class User {

    private String id;
    private String officeId;
    private String officeName;
    private List<Role> roles;
    private String loginName;
    private String phone;
    private String email;
    private String mobile;
    private String userType;
    private String areaCode;
    private Boolean isAdmin;    //是否管理员

}

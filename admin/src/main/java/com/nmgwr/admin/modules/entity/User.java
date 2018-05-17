package com.nmgwr.admin.modules.entity;

import lombok.Data;

//使用beetl的orm查询时继承TailBean，实现混合模型
//@OrmQuery(
//        {
//                @OrmCondition(
//                        target = SysRole.class,
//                        attr = "id",
//                        targetAttr = "userId",
//                        sqlId = "login.queryUserRoles",
//                        type = OrmQuery.Type.MANY
//                )
//        }
//)
@Data
public class User{

    private String id;
    private String name;
    private String officeId;
    private String officeName;
    private String loginName;
    private String phone;
    private String email;
    private String mobile;
    private String userType;
    private String areaCode;
    private Boolean isAdmin;    //是否管理员
    private String userStatus;
    private String passwd;
//    private List<Role> roles;


    //如果用户ID是0就是超级用户
    public Boolean getIsAdmin() {
        return id.equals("0") ? true : false;
    }

    //获取orm tails中的sysRole
//    public List<SysRole> getRoles(){
//        //先将orm查询中tails下的sysRole拿出来
//        List<SysRole> list = (List<SysRole>)this.getTails().get("sysRole");
//        //删掉tails下的sysRole
//        this.getTails().remove("sysRole");
//        return list;
//    }
}

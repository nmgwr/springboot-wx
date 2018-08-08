findList
===
*查询角色
select * from sys_role t where #use("condition")#

deleteRole
===
* 删除角色
UPDATE sys_role s SET s.update_user = #updateUser#,s.update_date = #updateDate#,s.del_flag = #delFlag# WHERE s.id = #id#

deleteRoleMenu
===
* 删除sys_role_menu中的role_id 和 menu_id
DELETE sys_role_menu FROM sys_role_menu LEFT JOIN sys_role ON sys_role_menu.role_id = sys_role.id WHERE sys_role.id = #id#

deleteUserRole
===
* 删除sys_user_role中的role_id和user_id
DELETE sys_user_role FROM sys_user_role LEFT JOIN sys_role ON sys_user_role.role_id = sys_role.id WHERE sys_role.id = #id#


updateRole
===
* 角色编辑
UPDATE sys_role s SET name = #name#, enname = #enname#,role_type = #roleType#,update_user = #updateUser#, update_date = #updateDate# WHERE id = #id# 



saveRole
===
* 新增一个角色
insert into sys_role(id,name,enname,create_user,role_type,create_date,update_user,update_date,remarks) 
VALUES(#id#,#name#,#enname#,#createUser#,#roleType#,#createDate#,#updateUser#,#updateDate#,#remarks#);


condition
===
* where条件区域适用于所有查询删除修改、不需要每次都写一遍
1=1
@if(!isEmpty(name)){
    and t.name like #'%' + name + '%'#
@}
@if(!isEmpty(enname)){
    and t.enname = #enname#
@}
@if(!isEmpty(roleType)){
    and t.role_type = #roleType#
@}

menuList
===
* 查询菜单列表
select name from sys_menu s where #use("condition")#

deleteMenu
===
* 删除菜单
UPDATE sys_menu sm SET sm.update_user = #updateUser#,sm.update_date = #updateDate#,sm.del_flag = #delFlag# WHERE sm.id = #id#

deleteRoleMenu
===
* 删除sys_role_menu中的role_id 和 menu_id
DELETE sys_role_menu FROM sys_role_menu LEFT JOIN sys_menu ON sys_role_menu.menu_id = sys_menu.id WHERE sys_menu.id = #id#


updateMenu
===
* 菜单编辑
UPDATE sys_menu s SET name = #name#, update_user = #updateUser#, update_date = #updateDate#, parent_id = #parentId#, href = #href#, icon = #icon#, is_show = #isShow#, type = #type#, permission = #permission# WHERE id = #id# 



saveMenu
===
* 新增一个菜单
insert into sys_menu(id,name,create_user,create_date,update_user,update_date,parent_id,href,icon,is_show,type,permission,remarks) 
VALUES(#id#,#name#,#createUser#,#createDate#,#updateUser#,#updateDate#,#parentId#,#href#,#icon#,#isShow#,#type#,#permission#,#remarks#);

condition
===
* where条件区域适用于所有查询删除修改、不需要每次都写一遍
1=1
@if(!isEmpty(id)){
    and s.id = #id#
@}
@if(!isEmpty(createUser)){
    and s.create_user = #createUser#
@}
@if(!isEmpty(createDate)){
    and s.create_date = #createDate#
@}
@if(!isEmpty(updateUser)){
    and s.update_user = #updateUser#
@}
@if(!isEmpty(updateDate)){
    and s.update_date = #updateDate#
@}
@if(!isEmpty(name)){
    and s.name = #name#
@}
@if(!isEmpty(parentId)){
    and s.parent_id = #parentId#
@}
@if(!isEmpty(href)){
    and s.href = #href#
@}
@if(!isEmpty(icon)){
    and s.icon = #icon#
@}
@if(!isEmpty(isShow)){
    and s.is_show = #isShow#
@}
@if(!isEmpty(type)){
    and s.type = #type#
@}
@if(!isEmpty(permission)){
    and s.permission = #permission#
@}
@if(!isEmpty(remarks)){
    and s.remarks = #remarks#
@}
@if(!isEmpty(delFlag)){
    and s.del_flag = #delFlag#
@}

menuList
===
* 查询菜单列表
select name from sys_menu s where #use("condition")#

deleteMenu
===
* 删除菜单

updateMenu
===
* 菜单编辑
UPDATE sys_menu s SET name = #name#, update_user = #updateUser#, update_date = #updateDate#, parent_id = #parentId#, href = #href#, icon = #icon#, is_show = #isShow#, type = #type#, permission = #permission#, remarks = #remarks# WHERE id = #id# 



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

updateCondition
===
* 菜单编辑set
1=1
@if(!isEmpty(createUser)){
    and s.create_user = #createUser#
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
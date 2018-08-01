findList
===
*查询角色信息
select * from sys_role t where #use("condition")#

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

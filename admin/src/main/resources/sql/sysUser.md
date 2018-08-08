findListPage
===
* 分页查询用户
select 
    @pageTag(){
        *
    @}
from sys_user t where #use("condition")#

deleteUser
===
* 删除用户
UPDATE sys_user s SET  s.update_user = #updateUser#,s.update_date = #updateDate#,s.del_flag = #delFlag# WHERE s.id = #id#

deleteUserRole
===
* 删除sys_user_role中的role_id 和 user_id
DELETE sys_user_role FROM sys_user_role LEFT JOIN sys_user ON sys_user_role.user_id = sys_user.id WHERE sys_user.id = #id#


updateUser
===
* 用户编辑
UPDATE sys_user s SET name = #name#, company_id = #companyId#, office_id = #officeId#, user_status = #userStatus#, phone = #phone#, email = #email#, mobile = #mobile#, user_type = #userType#, area_code = #areaCode#, update_user = #updateUser#, update_date = #updateDate# WHERE id = #id# 



saveUser
===
* 新增一个用户
insert into sys_user(id,name,company_id,office_id,user_status,login_name,phone,email,mobile,login_ip,login_date,area_code,create_user,create_date,update_user,update_date,remarks) 
VALUES(#id#,#name#,#companyId#,#officeId#,#userStatus#,#loginName#,#phone#,#email#,#mobile#,#loginIp#,#loginDate#,#areaCode#,#createUser#,#createDate#,#updateUser#,#updateDate#,#remarks#);

condition
===
* where条件区域适用于所有查询删除修改、不需要每次都写一遍
1=1
@if(!isEmpty(id)){
    and t.id = #id#
@}
@if(!isEmpty(name)){
    and t.name like #'%' + name + '%'#
@}
@if(!isEmpty(companyId)){
    and t.office_id = #companyId#
@}
@if(!isEmpty(officeId)){
    and t.office_id = #officeId#
@}
@if(!isEmpty(loginName)){
    and t.login_name = #loginName#
@}
@if(!isEmpty(phone)){
    and t.phone = #phone#
@}
@if(!isEmpty(email)){
    and t.email = #email#
@}
@if(!isEmpty(mobile)){
    and t.mobile = #mobile#
@}
@if(!isEmpty(userType)){
    and t.user_type = #userType#
@}
@if(!isEmpty(areaCode)){
    and t.area_code = #areaCode#
@}
@if(!isEmpty(delFlag)){
    and t.del_flag = #delFlag#
@}
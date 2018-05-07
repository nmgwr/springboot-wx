findListPage
===
* 分页查询用户
select 
    @pageTag(){
        *
    @}
from sys_user t where #use("condition")#

condition
===
* where条件区域适用于所有查询删除修改、不需要每次都写一边
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
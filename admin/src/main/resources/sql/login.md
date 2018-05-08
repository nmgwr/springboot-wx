login
===
* 登陆验证，验证通过返回用户实体信息，否则查不到记录返回null
SELECT u.id,
	u.office_id,
	o.name AS "office_name",
	u.login_name,
	u.phone,
	u.email,
	u.mobile,
	u.user_type,
	u.area_code,
	u.user_status
 FROM sys_user u LEFT JOIN sys_passwd p ON u.id = p.user_id LEFT JOIN sys_office o ON u.office_id = o.id 
WHERE u.login_name = #loginName# AND p.passwd = #passwd# ORDER BY p.update_date DESC LIMIT 1
 
queryUserRoles
===
* 查询用户角色，通过orm注解查询
SELECT DISTINCT sr.* FROM sys_role sr LEFT JOIN sys_user_role sur ON sr.id = sur.role_id WHERE sur.user_id=#userId#
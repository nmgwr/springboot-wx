##框架构想
    用最新的springboot搭建，集成一些优秀的插件，后期做分布式为服务再集成springcloud，
    该系统是一个后端系统，前端用vue单页面来开发。目前先开发后端admin模块，功能差不多开
    始开发前端。
##框架技术选型
* springboot2.0
* springMVC
* springAOP
* ibeetl（持久层）
* mysql
* HikariCP（数据源）
* lombok
* 后期还会陆陆续续集成其他优秀的插件框架
##框架组成
采用多模块的架构，不断的丰富和完善系统。不做那种大而全的东西。
* framework
    * 该模块是整体项目的父模块，下边的子模块都依赖该父模块，父模块中没有
    代码只有一个pom文件，在该pom文件中配置了所有模块的公共依赖。
* admin
    * 该模块是系统模块，主要做系统级的一些功能。包括用户、角色、菜单、登陆
    等等系统的一些操作
* wx
    * 该模块配置了系统的启动类，系统配置（系统启动类，配置放在业务子模块中，
    不放在admin中）  
##开发小组
* nmgwr
* nmgliuds
* zhaopz
##参与开发
如想参与开发请联系QQ652878952
##开发规范
* 开发工具采用idea
* 版本控制采用idea默认自带的git工具
* 上传代码不允许上传除项目以外的文件（idea的项目配置文件，class文件，target
目录下的文件，配置文件application等也不允许上传）
* jdk采用1.8（springboot2.0要求1.8）
* 每个方法、类都要加注释（注明参数、作者、返回），方法内重要代码要加注释。idea。      
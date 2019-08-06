电信NB-IoT的北向平台开发
该项目是基于Spring-boot框架的电信平台NB-IoT北向应用开发工程
安装Idea2018
安装Mysql5(或者8)
新建一个数据库名为nb_sql的数据库
导入nb_sql.sql文件(可选,自己需要更改部分代码)
注意事项：
CreateDeviceCommand.java中String deviceId = "112b7687-1d0c-4b22-b086-bcaf14e50b29";需要是云平台注册的数据
使用的profile文件请注意需要要求一致
电信最后的消息推送需要使用HTTPS的IP，这里推荐阿里云云主机
profile在文件夹内，在电信平台内导入

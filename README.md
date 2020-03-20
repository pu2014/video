# video
以视频播放为主要业务的练习项目，包含其它可以用到的技术等。如工作流，es，netty，ci等

## 包管理
- biz 中为业务代码
- admin 中为用户权限等
- common 为所有公用类型
- domain 为所有实体类
- profile 为个人信息
- resource 为 oauth2 客户端配置
- sso.server 为 oauth2 认证服务端配置
- upload 为文件上传服务

## Mybatis配置
配置了拦截器当修改时拦截，增加了自定义注解 `@CreateTime`,`@CreateBy`,`@UpdateTime`,`@UpdateBy`,`@Delete` 。

这些注解标识的实体类字段在插入和更新时会自动赋值，无需手动赋值。这 5 个字段为每张表中的基础字段，不涉及业务。

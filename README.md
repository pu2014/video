# video
以视频播放为主要业务的练习项目，包含其它可以用到的技术等。如工作流，es，netty，ci等

## 包管理
- biz 中为业务代码
  - article 文章的代码：demo，未开发完。
  - live 视频直播
- admin 中为用户权限等，设计到所有项目基础业务，未开发完。有根据需要的接口再继续开发。
- common 为所有公用类型
- domain 为所有实体类
- resource 为 oauth2 客户端配置
- sso.server 为 oauth2 认证服务端配置
- upload 为文件上传和下载服务

## Mybatis配置
配置了拦截器当修改时拦截，增加了自定义注解 `@CreateTime`,`@CreateBy`,`@UpdateTime`,`@UpdateBy`,`@Delete` 。

这些注解标识的实体类字段在插入和更新时会自动赋值，无需手动赋值。这 5 个字段为每张表中的基础字段，不涉及业务。

## 注意
所有的代码并未经过实际测试，只是经过思考应该有哪些包。如果需要用此项目二次开发，需要根据自己的业务接入前端。再进行二次开发。biz中全为业务代码，如不需要可以直接删除。其它除5张基础表和oauth2表都可删除。

## 接口
登录请求 token POST /video/oauth/token
```json
{
	username:username.value,
	password:txt_password.value,
	grant_type:'password',
	client_id:'client',
	client_secret:'secret'
}
```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="/common/header :: formHeader('表单')">  
</head>  
<body>

  <div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
  	<#if model_column ? exists>
	<#list model_column as model>
	<#if model.isPk = 'Y' >
		<input type="hidden" id="id" name="${model.changeColumnName?uncap_first}" />
	<#else>
		<div class="layui-form-item">
	      <label class="layui-form-label">登录名</label>
	      <div class="layui-input-inline">
	        <input type="text" id="${model.changeColumnName?uncap_first}" name="${model.changeColumnName?uncap_first}" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
	      </div>
	    </div>
  </#if>
</#list>
 </#if>
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-${table_name?lower_case}-back-submit" id="LAY-${table_name?lower_case}-back-submit" value="确认">
    </div>
  </div>

  <script>
  layui.config({
    base: layuiadmin //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
    var $ = layui.$
    ,form = layui.form ;
  })
  </script>
</body>
</html>
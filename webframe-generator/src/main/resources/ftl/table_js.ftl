layui.define(["table", "form"], function(e) {
	var t = layui.$,i = layui.table,$ = layui.$;
	layui.form;
	i.render({
		elem: "#LAY-${table_name?lower_case}-back-manage",
		url: ctxPath + "/${table_name?lower_case}/page",
		cols: [[
		 <#if model_column ? exists>
         <#list model_column as model>
         <#if model.isPk = 'Y' >
            	{
            		type: "checkbox",
					fixed: "left"
                }
    	<#else>
    		<#if model.changeColumnName != 'LogicDelete' && model.changeColumnName != 'CreateTime' && model.changeColumnName != 'CreateBy'>
	         	,{
                    field: "${model.changeColumnName?uncap_first}",
					title: "登录名"
    			}
	        </#if>    			
    	</#if>
    	</#list>
        </#if>
			,{
				title: "操作",
				width: 150,
				align: "center",
				fixed: "right",
				toolbar: "#table-useradmin-admin"
			}]
		],
		page:true,
		text: "对不起，加载出现异常！"
	}), i.on("tool(LAY-${table_name?lower_case}-back-manage)", function(e) {
		e.data;
		<#if model_column ? exists>
         <#list model_column as model>
         <#if model.isPk = 'Y' >
         	var id = e.data.${model.changeColumnName?uncap_first}
         </#if>
    	</#list>
        </#if>
		if("del" === e.event) layer.confirm("确定删除？", function(t) {
			$.ajax({
          	  url:ctxPath+'/${table_name?lower_case}/logicDelete',
          	  data:{"id":id},
          	  dataType:'json',
          	  type:'post',
          	  success:function(result){
          		  i.reload("LAY-${table_name?lower_case}-back-manage"),layer.close(t)										            		  
          	  }            	  
            });
		});
		else if("edit" === e.event) {
			t(e.tr);
			<#if model_column ? exists>
	         <#list model_column as model>
	         <#if model.isPk = 'Y' >
	         	var id = e.data.${model.changeColumnName?uncap_first};
	         <#else>
	         	<#if model.changeColumnName != 'LogicDelete' && model.changeColumnName != 'CreateTime' && model.changeColumnName != 'CreateBy' && model.changeColumnName != 'ModifyTime' && model.changeColumnName != 'ModifyBy'>
	         		var ${model.changeColumnName?uncap_first} = e.data.${model.changeColumnName?uncap_first};
	         	</#if>
	         </#if>
	    	</#list>
	        </#if>
			layer.open({
				type: 2,
				title: "编辑",
				content: ctxPath+"/${table_name?lower_case}/form",
				area: ["420px", "430px"],
				btn: ["确定", "取消"],
				yes: function(e, t) {
					var l = window["layui-layer-iframe" + e],
						r = "LAY-${table_name?lower_case}-back-submit",
						n = t.find("iframe").contents().find("#" + r);
					l.layui.form.on("submit(" + r + ")", function(t) {
						t.field;
						$.ajax({
			            	  url:ctxPath+'/${table_name?lower_case}/update',
			            	  data:t.field,
			            	  dataType:'json',
			            	  type:'post',
			            	  success:function(result){
			            		  i.reload("LAY-${table_name?lower_case}-back-manage"), layer.close(e)
			            	  }            	  
			              });  						
					}), n.trigger("click")
				},
				success: function(e, t) {
					var body = layui.layer.getChildFrame('body', t);
					<#if model_column ? exists>
			         <#list model_column as model>
			         <#if model.isPk = 'Y' >
			         	body.find("#id").val(id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
			         <#else>
			         	<#if model.changeColumnName != 'LogicDelete' && model.changeColumnName != 'CreateTime' && model.changeColumnName != 'CreateBy' && model.changeColumnName != 'ModifyTime' && model.changeColumnName != 'ModifyBy'>
			         		body.find("#${model.changeColumnName?uncap_first}").val(${model.changeColumnName?uncap_first});
			         	</#if>			         	
			         </#if>
			    	</#list>
			        </#if>
				}
			})
		}
	}), e("${table_name?lower_case}", {})
});
getListByExample
===
 select 
 <#if model_column ? exists>
    <#list model_column as model>
	<#if !model_has_next>
	t.${model.columnName}
	<#else>
	t.${model.columnName},
	</#if>
    </#list>
<#else>
    *
</#if>
    from ${tableName} t 
    where 1=1
    AND (t.logic_delete = 'N' OR t.logic_delete is Null)
    
getListByExample
===
 select 
 @pageTag(){
 <#if model_column ? exists>
    <#list model_column as model>
	<#if !model_has_next>
	t.${model.columnName}
	<#else>
	t.${model.columnName},
	</#if>
    </#list>
<#else>
    *
</#if>
 @}
    from ${tableName} t 
    where 1=1
    AND (t.logic_delete = 'N' OR t.logic_delete is Null)
package ${package_name}.entity;

import java.io.Serializable;
import java.util.Date;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.UpdateIgnore;
import org.beetl.sql.core.annotatoin.UpdateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webframe.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import com.webframe.beetlsql.CreateBy;

/**
* @author ${author}
* @date ${date}
*/
@Getter
@Setter
public class ${table_name} extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
<#if model_column ? exists>
    <#list model_column as model>
    <#if model.isPk = 'Y' >
    <#if model.columnType = 'LONG'>
    @AssignID("snowflake")
	private Long ${model.changeColumnName?uncap_first};	
	</#if>
    <#else>
    <#if model.changeColumnName != 'LogicDelete' && model.changeColumnName != 'CreateTime' && model.changeColumnName != 'CreateBy' && model.changeColumnName != 'ModifyTime' && model.changeColumnName != 'ModifyBy'>
    <#if model.columnType = 'LONG'>
	private Long ${model.changeColumnName?uncap_first};
	</#if>
    <#if model.columnType = 'STRING' >
    private String ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'DATE' >
    private Date ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'FLOAT' >
    private Float ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'DOUBLE' >
    private Double ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'BLOB' >
    private byte[] ${model.changeColumnName?uncap_first};
    </#if>
    </#if>
    </#if>
    </#list>
    
    @UpdateTime
	@UpdateIgnore
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    
	@UpdateTime
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;
	
	@CreateBy
	@UpdateIgnore
    private Long createBy;
    
    @CreateBy
    private Long modifyBy;
    
</#if>
}
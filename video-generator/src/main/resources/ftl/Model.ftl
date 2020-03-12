package ${package_name}.domain;

import lombok.Data;
import top.yinjinbiao.video.common.domain.BaseDomain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
* @author ${author}
* @date ${date}
*/
@Data
public class ${table_name} extends BaseDomain implements Serializable{

	private static final long serialVersionUID = 1L;
	
<#if model_column ? exists>
    <#list model_column as model>
    <#if model.isPk = 'Y' >
    <#if model.columnType = 'LONG'>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ${model.changeColumnName?uncap_first};	
	</#if>
    <#else>
    <#if model.changeColumnName != 'IsDelete' && model.changeColumnName != 'CreateTime' && model.changeColumnName != 'CreateBy' && model.changeColumnName != 'UpdateTime' && model.changeColumnName != 'UpdateBy'>
    <#if model.columnType = 'LONG'>
	private Long ${model.changeColumnName?uncap_first};
	</#if>
    <#if model.columnType = 'STRING' >
    private String ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'DATE' >
    private Date ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'DECIMAL' >
    private BigDecimal ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'BLOB' >
    private byte[] ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'TEXT' >
    private String ${model.changeColumnName?uncap_first};
    </#if>
    </#if>
    </#if>
    </#list>
    
</#if>
}
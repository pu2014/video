<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_name}.mapper.${table_name}Mapper">
    <resultMap id="BaseResultMap" type="${package_name}.domain.${table_name}">

        <#list model_column as model>
            <#if model.isPk = 'Y' >
                <#if model.columnType = 'LONG'>
        <id column="id" jdbcType="BIGINT" property="id" />
                </#if>
            <#else>
                <#if model.changeColumnName != 'IsDelete' && model.changeColumnName != 'CreateTime' && model.changeColumnName != 'CreateBy' && model.changeColumnName != 'UpdateTime' && model.changeColumnName != 'UpdateBy'>
                    <#if model.columnType = 'LONG'>
        <result column="${model.changeColumnName?lower_case}" jdbcType="BIGINT" property="${model.changeColumnName?uncap_first}" />
                    </#if>
                    <#if model.columnType = 'STRING' >
        <result column="${model.changeColumnName?lower_case}" jdbcType="VARCHAR" property="${model.changeColumnName?uncap_first}" />
                    </#if>
                    <#if model.columnType = 'DATE' >
        <result column="${model.changeColumnName?lower_case}" jdbcType="TIMESTAMP" property="${model.changeColumnName?uncap_first}" />
                    </#if>
                    <#if model.columnType = 'DECIMAL' >
        <result column="${model.changeColumnName?lower_case}" jdbcType="DECIMAL" property="${model.changeColumnName?uncap_first}" />
                    </#if>
                    <#if model.columnType = 'BLOB' >
        <result column="${model.changeColumnName?lower_case}" jdbcType="BLOB" property="${model.changeColumnName?uncap_first}" />
                    </#if>
                    <#if model.columnType = 'TEXT' >
        <result column="${model.changeColumnName?lower_case}" jdbcType="CLOB" property="${model.changeColumnName?uncap_first}" />
                    </#if>
                </#if>
            </#if>
        </#list>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime" />
        <result column="create_by" jdbcType="BIGINT" property="createBy" />
        <result column="update_time" jdbcType="TIMESTAMP" property="updateTime" />
        <result column="update_by" jdbcType="BIGINT" property="updateBy" />
        <result column="is_delete" jdbcType="TINYINT" property="delete" />
    </resultMap>
</mapper>
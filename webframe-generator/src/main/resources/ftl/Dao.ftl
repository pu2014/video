package ${package_name}.dao;

import java.util.List;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import ${package_name}.entity.${table_name};

/**
* @author ${author}
* @date ${date}
*/

public interface ${table_name}Dao extends BaseMapper<${table_name}>{

	List<${table_name}> getListByExample(@Param("${table_name?lower_case}")${table_name} entity);
	
	void queryByCondtion(PageQuery<${table_name}> query);

}
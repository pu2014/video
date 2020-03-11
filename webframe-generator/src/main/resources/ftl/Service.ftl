package ${package_name}.service;

import java.util.List;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webframe.enums.LogicDeleteEnum;

import ${package_name}.dao.${table_name}Dao;
import ${package_name}.entity.${table_name};

/**
* @author ${author}
* @date ${date}
*/

@Service
public class ${table_name}Service{

	@Autowired
	private ${table_name}Dao ${table_name?uncap_first}Dao;
	
	public void save(${table_name} entity) {
        ${table_name?uncap_first}Dao.insert(entity);
    }
    
	public void update(${table_name} entity) {
        ${table_name?uncap_first}Dao.updateById(entity);
    }
    
	public ${table_name} getOne(Long id) {		
        return ${table_name?uncap_first}Dao.single(id);
    }
    
	public void logicDelete(Long id){
		${table_name} single = ${table_name?uncap_first}Dao.single(id);
		if(single!=null){
			single.setLogicDelete(LogicDeleteEnum.Y);
			${table_name?uncap_first}Dao.updateById(single);			
		}
	}
	
	public void logicDeleteBatch(List<Long> list){
		for (Long id : list) {
			${table_name} single = ${table_name?uncap_first}Dao.single(id);
			if(single!=null){
				single.setLogicDelete(LogicDeleteEnum.Y);
				${table_name?uncap_first}Dao.updateById(single);			
			}
		}
	}
	
	public List<${table_name}> getList(${table_name} entity){
		entity.setLogicDelete(LogicDeleteEnum.N);
		return ${table_name?uncap_first}Dao.template(entity);
	}
	
	public List<${table_name}> getListByExample(${table_name} entity){
		return ${table_name?uncap_first}Dao.getListByExample(entity);
	}

	public void getPageByExample(PageQuery<${table_name}> query) {
		${table_name?uncap_first}Dao.queryByCondtion(query);
	}
}
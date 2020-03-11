package ${package_name}.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.webframe.vo.JsonResult;
import org.beetl.sql.core.engine.PageQuery;

import ${package_name}.entity.${table_name};
import ${package_name}.service.${table_name}Service;


@Controller
@RequestMapping(value = "/${table_name?lower_case}")
public class ${table_name}Controller {
	
	@Autowired
	private ${table_name}Service ${table_name?uncap_first}Service;
	
	@RequiresPermissions("${table_name?lower_case}")
	@RequestMapping("/index")
	public String index(){
		return "${table_name?lower_case}/${table_name?lower_case}";
	}
	
	@RequestMapping("/form")
	public String form(){
		return "${table_name?lower_case}/${table_name?lower_case}form";
	}	
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public JsonResult save(${table_name} entity){
		${table_name?uncap_first}Service.save(entity);
		return new JsonResult();
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public JsonResult update(${table_name} entity){
		${table_name?uncap_first}Service.update(entity);
		return new JsonResult();
	}
	
	/**
	 * 单条查询
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	public JsonResult getOne(Long id){
		${table_name} entity = ${table_name?uncap_first}Service.getOne(id);
		return new JsonResult(0,"查询成功",entity);
	}
	
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/logicDelete")
	@ResponseBody
	public JsonResult logicDelete(Long id){
		${table_name?uncap_first}Service.logicDelete(id);
		return new JsonResult(0,"删除成功");
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public JsonResult list(${table_name} entity){
		List<${table_name}> list = ${table_name?uncap_first}Service.getList(entity);
		return new JsonResult(0,"查询成功",list);
	}
	
	/**
	 * 分页查询
	 * @param username
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
    public JsonResult page(@RequestParam(value="page",defaultValue="1",required=true)long page,
    		@RequestParam(value="limit",defaultValue="10",required=true)long limit,${table_name} entity){
        PageQuery<${table_name}> query = new PageQuery<${table_name}>(page,limit,entity);
		${table_name?uncap_first}Service.getPageByExample(query);
        return new JsonResult(0,"查询成功",query.getList(),query.getTotalRow());       
    }
    
    /**
	 * 逻辑删除一批数据
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/logicDeleteBatch")
	@ResponseBody
	public JsonResult logicDeleteBatch(@RequestBody List<Long> ids){
		${table_name?uncap_first}Service.logicDeleteBatch(ids);
		return new JsonResult(0,"删除成功");
	}
}
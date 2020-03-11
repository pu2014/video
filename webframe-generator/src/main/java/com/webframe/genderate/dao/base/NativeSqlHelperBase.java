package com.webframe.genderate.dao.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.webframe.genderate.common.bean.PageObject;
import com.webframe.genderate.common.util.ReflectUtil;
import com.webframe.genderate.common.util.SqlUtil;




@Repository("NativeSqlHelperBase")
public class NativeSqlHelperBase {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*********** 获取单个对象   ********/
	
	public Object getSingle(String sql) {
		return getSingle(sql,new Object[]{});
	}

	public Object getSingle(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		
		List<?> list = query.getResultList();
		if (list != null && list.size() > 0) {
			Object o = (Object) list.get(0);
			return o;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取实体bean
	 * @param sql
	 * @param type
	 * @return
	 */
	public <T> T getSingle(String sql, Class<T> type) {
		return getSingle(sql,new Object[]{},type);
	}

	public <T> T getSingle(String sql, Object[] params, Class<T> type) {
		List<T> list = queryByNativeSQL(sql,params, type);
		if(list != null && list.size() != 0){
			return list.get(0);
		}
		return null;
	}
	

	
	
	/**
	 * 获取map数组
	 * @param sql
	 * @param rowStartIdxAndCount
	 * @return
	 */
	public List getMapList(String sql, Integer... rowStartIdxAndCount) {
		return getMapList(sql, new Object[]{},rowStartIdxAndCount);
	}

	public List getMapList(String sql, Object[] params, Integer... rowStartIdxAndCount) {
		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		
		if(rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0){
			if(rowStartIdxAndCount[0] != null && rowStartIdxAndCount[0] > 0){
				query.setFirstResult(rowStartIdxAndCount[0]);
			}
			if(rowStartIdxAndCount[1] != null && rowStartIdxAndCount[1] > 0){
				query.setMaxResults(rowStartIdxAndCount[1]);
			}
		}
		
		List list = query.getResultList();
		List newList = parseList(list);
		entityManager.clear();
		return newList;
	}
	
	
	/**
	 * 获取实体bean数组
	 * @param sql
	 * @param type
	 * @return
	 */
	public <T> List<T> queryByNativeSQL(String sql, Class<T> type) {
		return queryByNativeSQL(sql,null, type);
	}
	
	
	public <T> List<T> queryByNativeSQL(String sql,Object[] params, Class<T> type) {
		Query query = entityManager.createNativeQuery(sql,type);
		
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		
		List list = query.getResultList();
		entityManager.clear();
		return list;
	}
	
	/**
	 * 获取object数组
	 * @param sql
	 * @return
	 */
	public List queryByNativeSQL(String sql) {
		return queryByNativeSQL(sql,new Object[]{});
	}
	
	public List queryByNativeSQL(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		List list = query.getResultList();
		entityManager.clear();
		return list;
	}

	
	/********************** 执行sql  ************************/
	

	public int exeNativeSQL(String sql) {
		return exeNativeSQL(sql,null);
	}

	public int exeNativeSQL(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.executeUpdate();
	}


	
	public boolean exeNativeSQL(List<String> sqls) {
		boolean b = false;
		int i = 0;
		for (Object sql : sqls) {
			i++;
			if (sql != null) {
				Query query = entityManager.createNativeQuery(sql.toString());
				query.executeUpdate();
			}
			if (i % 20 == 0) {
				entityManager.flush();
			}
		}

		b = true;
		return b;
	}
	
	
	
	/**
	 * sql语句查询分页
	 * @param sql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageObject getPageObject(String sql, Object[] params, Integer... rowStartIdxAndCount) {
		
		int curPage = 1;
		int pageSize = 999999;
		if(rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0){
			if(rowStartIdxAndCount[0] != null){
				curPage = rowStartIdxAndCount[0];
			}
			if(rowStartIdxAndCount[1] != null){
				pageSize = rowStartIdxAndCount[1];
			}
		}else{
			rowStartIdxAndCount = new Integer[2];
		}
		Integer start = (curPage - 1) * pageSize;
		rowStartIdxAndCount[0] = start;
		
		String sqlCount = SqlUtil.getCountSql(sql);
		int count = Integer.parseInt(this.getSingle(sqlCount, params).toString());
		int totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		
		PageObject pageobject = new PageObject();
		pageobject.setTotal(count);
		pageobject.setPageNumber(totalPage);
		pageobject.setCurrpage(curPage);
		if(count > 0){
			List list = this.getMapList(sql, params,rowStartIdxAndCount);
			pageobject.setRows(list);
		}
		return pageobject;
	}
	
	/**
	 * 获取驼峰格式的map数组
	 * @param list  原数组
	 * @return
	 */
	private List<Map<Object,Object>> parseList(List<Map<Object,Object>> list) {

		List<Map<Object,Object>> newList = new ArrayList<Map<Object,Object>>();
		if(list.size() == 0){
			return newList;
		}
		
		Object[] keys = list.get(0).keySet().toArray();
		Object[] newKeys = new Object[keys.length];
		for(int i = 0; i < keys.length; i++){
			newKeys[i] = SqlUtil.columnToProperty(keys[i].toString());
		}
		
		
		Map<Object,Object> newMap = null;
		for(Map<Object,Object> tmp : list){
			newMap = new HashMap<Object, Object>();
			for(int i = 0; i < keys.length; i++){
				newMap.put(newKeys[i], tmp.get(keys[i]));
			}
			newList.add(newMap);
		}
		
		return newList;
	}
	
/*************** 获取数组   ****************/
	
	/**
	 * 获取自定义bean数组
	 * @param sql
	 * @param cls
	 * @return
	 */
	public <T> List<T> getBeanList(String sql,Class<T> cls){
		return getBeanList(sql,null, cls);
	}
	
	public <T> List<T> getBeanList(String sql,Object[] params,Class<T> cls){
		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		
		List list = query.getResultList();
		List newList = parseList(list);
		List<T> beanList = ReflectUtil.mapListToBeanList(newList, cls);
		entityManager.clear();
		return beanList;
	}
	
}

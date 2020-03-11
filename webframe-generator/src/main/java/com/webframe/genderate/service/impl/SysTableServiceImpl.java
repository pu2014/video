package com.webframe.genderate.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.webframe.genderate.common.bean.ColumnClass;
import com.webframe.genderate.common.bean.PageObject;
import com.webframe.genderate.common.bean.ResponseMsg;
import com.webframe.genderate.common.util.CodeGenerateUtils;
import com.webframe.genderate.common.util.StringUtil;
import com.webframe.genderate.dao.base.NativeSqlHelperBase;
import com.webframe.genderate.service.SysTableService;



@Service("SysTableServiceImpl")
@Transactional
public class SysTableServiceImpl implements SysTableService{

	@Autowired
	private NativeSqlHelperBase sqlHelper;
	@Autowired
	public CodeGenerateUtils codeGenerateUtils;
	@Value("${db.schema}")
	private String dbSchema;

	@Override
	public PageObject query(String fuzzy, int page, int rows) {
		PageObject pageObject = new PageObject();
		List<String> params = new ArrayList<>();
		String sql = "SELECT\n" +
				"	t.TABLE_NAME,\n" +
				"	date_format(t.CREATE_TIME, '%Y-%m-%d %H:%i:%s') as CREATE_TIME,\n" +
				"	t.UPDATE_TIME,\n" +
				"	t.TABLE_COMMENT,\n" +
				"	t.TABLE_TYPE\n" +
				"FROM\n" +
				"	information_schema. TABLES t\n" +
				"WHERE\n" +
				"	table_schema = ?\n";
		params.add(dbSchema);
		if(!StringUtil.isEmpty(fuzzy)){
			sql += " AND t.TABLE_NAME like ?\n";
			params.add(StringUtil.getLikeStr(fuzzy));
		}
		sql += " ORDER BY\n" +
			   "	t.table_name DESC";
		pageObject = sqlHelper.getPageObject(sql, params.toArray(), page, rows);
		return pageObject;
	}
	
	@Override
	public PageObject queryColumn(String tableName, int page, int rows){
		PageObject pageObject = new PageObject();
		List<String> params = new ArrayList<>();
		String sql = "SELECT\n" +
				"	t.column_name,\n" +
				"	t.COLUMN_TYPE,\n" +
				"	case when t.COLUMN_KEY = 'PRI' then 'Y' else 'N' end as is_pk,\n" +
				"	t.COLUMN_COMMENT\n" +
				"FROM\n" +
				"	information_schema. COLUMNS t\n" +
				"WHERE\n" +
				"	table_schema = ?\n" +
				"AND table_name = ?";
		params.add(dbSchema);
		params.add(tableName);
		pageObject = sqlHelper.getPageObject(sql, params.toArray(), page, rows);
		return pageObject;
	}

	@Override
	public ResponseMsg generate(String tableName, String diskPath, String path) {
		List<String> params = new ArrayList<>();
		String sql = "SELECT\n" +
				"	t.column_name,\n" +
				"	t.COLUMN_TYPE,\n" +
				"	case when t.COLUMN_KEY = 'PRI' then 'Y' else 'N' end as is_pk,\n" +
				"	t.COLUMN_COMMENT\n" +
				"FROM\n" +
				"	information_schema. COLUMNS t\n" +
				"WHERE\n" +
				"	table_schema = ?\n" +
				"AND table_name = ?";
		params.add(dbSchema);
		params.add(tableName);
		List<ColumnClass> list = sqlHelper.getBeanList(sql, params.toArray(), ColumnClass.class);
		try {
			codeGenerateUtils.generate(tableName, list, path, diskPath);
			return new ResponseMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMsg(false, "代码生成失败");
	}
	
}

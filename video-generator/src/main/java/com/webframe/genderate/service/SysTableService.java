package com.webframe.genderate.service;

import com.webframe.genderate.common.bean.PageObject;
import com.webframe.genderate.common.bean.ResponseMsg;

public interface SysTableService {

	public PageObject query(String fuzzy, int page, int rows);
	
	public PageObject queryColumn(String tableName, int page, int rows);
	
	public ResponseMsg generate(String tableName, String diskPath, String path);
}

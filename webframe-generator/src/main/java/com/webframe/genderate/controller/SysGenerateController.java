package com.webframe.genderate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webframe.genderate.common.annotation.Log;
import com.webframe.genderate.common.bean.PageObject;
import com.webframe.genderate.common.bean.ResponseMsg;
import com.webframe.genderate.service.SysTableService;



@Controller
@RequestMapping(value = "/sysGenerate")
public class SysGenerateController {
	
	@Autowired
	private SysTableService sysTableService;
	
	@RequestMapping(value = "/index")
	public String index(){
		return "systools/tableinfo";
	}
	
	@RequestMapping(value = "/columninfo")
	public String column(){
		return "systools/columninfo";
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	@Log("数据库表信息查询")
	public PageObject query(String fuzzy, int page, int rows, 
			HttpServletRequest request, HttpServletResponse response){
		return sysTableService.query(fuzzy, page, rows);
	}
	
	@RequestMapping(value = "/queryColumn")
	@ResponseBody
	@Log("列信息查询")
	public PageObject queryColumn(String tableName, int page, int rows, 
			HttpServletRequest request, HttpServletResponse response){
		return sysTableService.queryColumn(tableName, page, rows);
	}
	
	@RequestMapping(value = "/generate")
	@ResponseBody
	@Log("代码生成")
	public ResponseMsg generate(String tableName, String diskPath, String path){
		return sysTableService.generate(tableName, diskPath, path);
	}
}

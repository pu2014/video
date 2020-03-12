package com.webframe.genderate.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//总页面数
	private int pageNumber=0;
	//当前页
	private int currpage=0;
	//总记录数
	private long total;
	//记录
	private List<Object> rows = new ArrayList<Object>();
	
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}
	public List<Object> getRows() {
		return rows;
	}
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
}

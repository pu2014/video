package com.webframe.genderate.common.bean;

public class ColumnClass {

	private String columnName; //列名
	private String columnType;	//类型
	private String changeColumnName;	// 数据库字段首字母小写且去掉下划线字符串
	private String isPk;	//是否主键
	private String columnComment;	//备注
	
	public ColumnClass() {
		super();
	}

	public ColumnClass(String columnName, String columnType, String changeColumnName, String isPk,
			String columnComment) {
		super();
		this.columnName = columnName;
		this.columnType = columnType;
		this.changeColumnName = changeColumnName;
		this.isPk = isPk;
		this.columnComment = columnComment;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getChangeColumnName() {
		return changeColumnName;
	}

	public void setChangeColumnName(String changeColumnName) {
		this.changeColumnName = changeColumnName;
	}
	
}

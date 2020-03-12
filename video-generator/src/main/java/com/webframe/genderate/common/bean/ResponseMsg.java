package com.webframe.genderate.common.bean;

/**
 * 系统返回消息类
 * @author jczhou
 *
 */
public class ResponseMsg {

	// 是否成功
	private boolean success;
	// 消息
	private String msg;
	// 数据
	private Object data;

	public ResponseMsg() {
		this.success = true;
		this.msg = "操作成功！";
	}

	public ResponseMsg(String msg) {
		this.success = true;
		this.msg = msg;
	}

	public ResponseMsg(Object data) {
		this.success = true;
		this.data = data;
	}

	public ResponseMsg(String msg, Object data) {
		this.success = true;
		this.msg = msg;
		this.data = data;
	}

	public ResponseMsg(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public ResponseMsg(boolean success, String msg, Object data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

package top.yinjinbiao.video.common.enums;

public enum ChatRequestOperatorEnum {
	
	UNPASS(0,"忽略"),PASS(1,"通过");
	
	private final int value;
	private final String msg;
	
	ChatRequestOperatorEnum(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public int value() {
		return value;
	}

	public String msg() {
		return msg;
	}
}

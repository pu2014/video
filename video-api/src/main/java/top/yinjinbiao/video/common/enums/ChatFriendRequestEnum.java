package top.yinjinbiao.video.common.enums;

public enum ChatFriendRequestEnum {
	
	NOT_FOUND(1,"当前查找账号不存在!"),ADDED(2,"该用户已经是您的好友!"),OK(3,"成功发送好友申请!");
	
	private final int value;
	private final String msg;
	
	ChatFriendRequestEnum(int value, String msg){
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

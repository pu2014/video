package top.yinjinbiao.video.common.enums;

/**
 * chat模块消息类型
 * @author yin.jinbiao
 *
 */
public enum ChatMsgActionEnum {

	CONNECT(1, "第一次(或重连)初始化连接"),
	CHAT(2, "聊天消息"),	
	SIGNED(3, "消息签收"),
	KEEPALIVE(4, "客户端保持心跳"),
	PULL_FRIEND(5, "拉取好友");
	
	private final Integer type;
	private final String content;
	
	ChatMsgActionEnum(Integer type, String content){
		this.type = type;
		this.content = content;
	}

	public Integer type() {
		return type;
	}

	public String content() {
		return content;
	}
	
	

}

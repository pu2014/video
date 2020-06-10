package top.yinjinbiao.video.netty;

import java.io.Serializable;

import lombok.Data;

/**
 * Chat消息体
 * @author yin.jinbiao
 *
 */
@Data
public class ChatMsg implements Serializable {

	private static final long serialVersionUID = 3611169682695799175L;
	
	private Long senderId;		// 发送者的用户id	
	private Long receiverId;		// 接受者的用户id
	private String msg;				// 聊天内容
	private Long msgId;			// 用于消息的签收
	
}

package top.yinjinbiao.video.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import top.yinjinbiao.video.biz.chat.service.BizChatRecordService;
import top.yinjinbiao.video.common.enums.ChatMsgActionEnum;
import top.yinjinbiao.video.common.enums.ChatMsgSignEnum;
import top.yinjinbiao.video.common.util.SpringUtil;
import top.yinjinbiao.video.domain.BizChatRecord;

/**
 * 
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	private static Map<Long, Channel> userChannelRel = new ConcurrentHashMap<>();

	// 用于记录和管理所有客户端的channle
	public static ChannelGroup users = 
			new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) 
			throws Exception {
		// 获取客户端传输过来的消息
		String content = msg.text();
		
		Channel currentChannel = ctx.channel();

		// 1. 获取客户端发来的消息
		ObjectMapper om = new ObjectMapper();
		DataContent dataContent = om.readValue(content, DataContent.class);
		Integer action = dataContent.getAction();
		// 2. 判断消息类型，根据不同的类型来处理不同的业务

		if (action == ChatMsgActionEnum.CONNECT.type()) {
			// 	2.1  当websocket 第一次open的时候，初始化channel，把用的channel和userid关联起来
			Long senderId = dataContent.getChatMsg().getSenderId();
			userChannelRel.put(senderId, currentChannel);
		} else if (action == ChatMsgActionEnum.CHAT.type()) {
			//  2.2  聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
			ChatMsg chatMsg = dataContent.getChatMsg();
			// 构造实体
			BizChatRecord record = new BizChatRecord(null, chatMsg.getSenderId(), chatMsg.getReceiverId(), chatMsg.getMsg(), ChatMsgSignEnum.UNSIGN.value());
			
			// 保存消息到数据库，并且标记为 未签收
			BizChatRecordService chatRecordService = (BizChatRecordService)SpringUtil.getBean("bizChatRecordServiceImpl");
			Long msgId = chatRecordService.save(record);
			chatMsg.setMsgId(msgId);
			
			DataContent dataContentMsg = new DataContent();
			dataContentMsg.setChatMsg(chatMsg);
			
			// 发送消息
			// 从全局用户Channel关系中获取接受方的channel
			Channel receiverChannel = userChannelRel.get(chatMsg.getReceiverId());
			if (receiverChannel == null) {
				// TODO channel为空代表用户离线，推送消息（JPush，个推，小米推送）
			} else {
				// 当receiverChannel不为空的时候，从ChannelGroup去查找对应的channel是否存在
				Channel findChannel = users.find(receiverChannel.id());
				if (findChannel != null) {
					// 用户在线
					receiverChannel.writeAndFlush(
							new TextWebSocketFrame(om.writeValueAsString(dataContentMsg)));
				} else {
					// 用户离线 TODO 推送消息
				}
			}
			
		} else if (action == ChatMsgActionEnum.SIGNED.type()) {
			//  2.3  签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]
			BizChatRecordService chatRecordService = (BizChatRecordService)SpringUtil.getBean("bizChatRecordServiceImpl");
			// 扩展字段在signed类型的消息中，代表需要去签收的消息id，逗号间隔
			String msgIdsStr = dataContent.getExtand();
			String[] msgIds = msgIdsStr.split(",");
			
			List<Long> msgIdList = new ArrayList<>();
			for (String mid : msgIds) {
				if (StringUtils.isNotBlank(mid)) {
					msgIdList.add(Long.parseLong(mid));
				}
			}
			
			System.out.println(msgIdList.toString());
			
			if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() > 0) {
				// 批量签收
				chatRecordService.updateMsgSigned(msgIdList);
			}
			
		} else if (action == ChatMsgActionEnum.KEEPALIVE.type()) {
			//  2.4  心跳类型的消息
			System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");
		}
	}
	
	/**
	 * 当客户端连接服务端之后（打开连接）
	 * 获取客户端的channle，并且放到ChannelGroup中去进行管理
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端添加，channelId为：" + channelId);
		users.add(ctx.channel());
	}

	/**
	 * 客户端移除的处理
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {		
		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端被移除，channelId为：" + channelId);		
		// 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
		users.remove(ctx.channel());
	}

	/**
	 * 发生异常的捕获处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		// 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
		ctx.channel().close();
		users.remove(ctx.channel());
	}
}

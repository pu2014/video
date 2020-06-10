package top.yinjinbiao.video.biz.chat.service;

import java.util.List;

import top.yinjinbiao.video.domain.BizChatRecord;

public interface BizChatRecordService {
	
	/**
	 * 保存消息记录
	 * @param record
	 * @return
	 */
	Long save(BizChatRecord record);

	/**
	 * 批量设置已读
	 * @param msgIdList
	 */
	void updateMsgSigned(List<Long> msgIdList);

}

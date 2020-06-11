package top.yinjinbiao.video.biz.chat.mapper;

import java.util.List;

import top.yinjinbiao.video.domain.BizChatRecord;

public interface BizChatRecordMapper {

    int deleteByPrimaryKey(Long id);
   
    int insert(BizChatRecord record);
   
    int insertSelective(BizChatRecord record);

    BizChatRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizChatRecord record);

    int updateByPrimaryKey(BizChatRecord record);

    /**
     * 批量设置消息为已读
     * @param msgIdList
     */
	void updateMsgSignedById(List<Long> msgIdList);

	/**
	 * 根据当前登陆人查询未读消息
	 * @param currentUserId
	 * @return
	 */
	List<BizChatRecord> listUnReadChatRecord(Long acceptUserId);
}
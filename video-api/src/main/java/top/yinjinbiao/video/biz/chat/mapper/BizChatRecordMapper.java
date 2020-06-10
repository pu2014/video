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

	void updateMsgSignedById(List<Long> msgIdList);
}
package top.yinjinbiao.video.biz.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yinjinbiao.video.biz.chat.mapper.BizChatRecordMapper;
import top.yinjinbiao.video.biz.chat.service.BizChatRecordService;
import top.yinjinbiao.video.domain.BizChatRecord;

@Service
public class BizChatRecordServiceImpl implements BizChatRecordService {
	
	@Autowired
	private BizChatRecordMapper bizChatRecordMapper;
	
	@Override
	public Long save(BizChatRecord record) {
		bizChatRecordMapper.insert(record);
		return record.getId();
	}

	@Override
	public void updateMsgSigned(List<Long> msgIdList) {
		bizChatRecordMapper.updateMsgSignedById(msgIdList);	
	}

	@Override
	public List<BizChatRecord> listUnReadChatRecord(Long acceptUserId) {
		return bizChatRecordMapper.listUnReadChatRecord(acceptUserId);
	}

}

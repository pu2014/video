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
	private BizChatRecordMapper bizChatMapper;

	@Override
	public Long save(BizChatRecord record) {
		bizChatMapper.insert(record);
		return record.getId();
	}

	@Override
	public void updateMsgSigned(List<Long> msgIdList) {
		bizChatMapper.updateMsgSignedById(msgIdList);	
	}

}

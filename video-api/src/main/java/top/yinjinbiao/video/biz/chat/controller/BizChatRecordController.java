package top.yinjinbiao.video.biz.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.yinjinbiao.video.biz.chat.service.BizChatRecordService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.common.util.UserLocalUtil;
import top.yinjinbiao.video.domain.BizChatRecord;

@RestController
@RequestMapping("/chat")
public class BizChatRecordController {
	
	@Autowired
	private BizChatRecordService bizChatRecordService;
	
	/**
	 * 查询未读消息
	 * @return
	 */
	@GetMapping("/getUnReadMsgList")
	public ResponseResult<List<BizChatRecord>> unReadMsgList(){
		return new ResponseResult<>(HttpStatus.OK.value(),bizChatRecordService.listUnReadChatRecord(UserLocalUtil.getCurrentUserId()));
		
	}

}

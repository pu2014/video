package top.yinjinbiao.video.biz.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.yinjinbiao.video.biz.chat.service.BizChatRecordService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.domain.BizChatRecord;

@RestController
@RequestMapping("/chat")
public class BizChatRecordController {
	
	@Autowired
	private BizChatRecordService bizChatRecordService;
	
	@PostMapping("/save")
	public ResponseResult<Long> save(){
		Long save = bizChatRecordService.save(new BizChatRecord(null,1l,1l,"test",false));
		return new ResponseResult<>(HttpStatus.OK.value(),"保存成功",save);
	}

}

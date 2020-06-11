package top.yinjinbiao.video.biz.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.yinjinbiao.video.biz.chat.service.BizFriendRequestService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.common.util.UserLocalUtil;
import top.yinjinbiao.video.domain.BizFriendRequest;

@RestController
@RequestMapping("/chat")
public class BizFriendRequestController {

	@Autowired
	private BizFriendRequestService bizFriendRequestService;
	
	@GetMapping("/queryFriendRequests")
	public ResponseResult<List<BizFriendRequest>> queryFriendRequests(){
		return new ResponseResult<>(HttpStatus.OK.value(),bizFriendRequestService.queryFriendRequests(UserLocalUtil.getCurrentUserId()));
	}
}

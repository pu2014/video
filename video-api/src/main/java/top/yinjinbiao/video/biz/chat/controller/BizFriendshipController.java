package top.yinjinbiao.video.biz.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.yinjinbiao.video.biz.chat.service.BizFriendshipService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.common.util.UserLocalUtil;
import top.yinjinbiao.video.domain.vo.SysUserVO;

@RestController
@RequestMapping("/chat")
public class BizFriendshipController {
	
	@Autowired
	private BizFriendshipService bizFriendshipService;
	
	/**
	 * 查询我的好友列表
	 * @return
	 */
	@GetMapping("/myFriends")
	public ResponseResult<List<SysUserVO>> myFriends(){
		Long userId = UserLocalUtil.getCurrentUserDetails().getId();//当前登陆人id
		List<SysUserVO> myFriendList = bizFriendshipService.findMyFriendsById(userId);
		return new ResponseResult<>(HttpStatus.OK.value(),"查询成功",myFriendList);
	}
	
}

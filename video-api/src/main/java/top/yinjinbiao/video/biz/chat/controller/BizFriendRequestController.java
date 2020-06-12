package top.yinjinbiao.video.biz.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.yinjinbiao.video.admin.service.SysUserService;
import top.yinjinbiao.video.biz.chat.service.BizFriendRequestService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.common.enums.ChatFriendRequestEnum;
import top.yinjinbiao.video.common.util.UserLocalUtil;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.domain.vo.SysUserVO;

@RestController
@RequestMapping("/chat")
public class BizFriendRequestController {
	
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private BizFriendRequestService bizFriendRequestService;
	
	/**
	 * 查询好友申请
	 * @return
	 */
	
	@GetMapping("/queryFriendRequests")
	public ResponseResult<List<SysUserVO>> queryFriendRequests(){
		List<SysUserVO> friendRequestsList = bizFriendRequestService.queryFriendRequests(UserLocalUtil.getCurrentUserId());
		return new ResponseResult<>(HttpStatus.OK.value(),friendRequestsList);
	}
	
	/**
	 * 发现好友
	 * @return
	 */
	@GetMapping("/search")
	public ResponseResult<SysUserVO> search(String friendUsername){
		SysUser sysUser = sysUserService.findByUsername(friendUsername);
		if(sysUser==null){
			return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(),"该用户不存在!");
		}else{
			return new ResponseResult<>(HttpStatus.OK.value(),"查询成功",new SysUserVO(sysUser.getId(),sysUser.getNickname(),sysUser.getUsername()));			
		}
	}
	
	/**
	 * 发送好友申请
	 * @return
	 */
	@PostMapping("/addFriendRequest")
	public ResponseResult<SysUserVO> friendRequest(Long friendUserId){
		Long myUserId = UserLocalUtil.getCurrentUserDetails().getId();//当前登陆人id
		// 如果的好友申请是我本人，直接返回错误信息。
		if(myUserId==friendUserId){
			return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), "不能添加自己为好友！", null);
		}
		int status = bizFriendRequestService.saveFriendRequest(myUserId,friendUserId);		
		if(ChatFriendRequestEnum.ADDED.value()==status){
			return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), ChatFriendRequestEnum.ADDED.msg(), null);
		}else if(ChatFriendRequestEnum.NOT_FOUND.value()==status){
			return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), ChatFriendRequestEnum.NOT_FOUND.msg(), null);
		}else{
			return new ResponseResult<>(HttpStatus.OK.value(), ChatFriendRequestEnum.OK.msg(), null);
		}		
	}
	
	/**
	 * 操作好友的申请
	 * @param friendUserId
	 * @param operateType
	 * @return
	 */
	@PostMapping("/operateFriendRequest")
	public ResponseResult<List<SysUserVO>> operateFriendRequest(Long friendUserId,Integer operateType){
		Long myUserId = UserLocalUtil.getCurrentUserDetails().getId();//当前登陆人id
		List<SysUserVO> myFriendList = bizFriendRequestService.operateFriendRequest(myUserId,friendUserId,operateType);
		return new ResponseResult<>(HttpStatus.OK.value(),"添加成功",myFriendList);
	}
	
}

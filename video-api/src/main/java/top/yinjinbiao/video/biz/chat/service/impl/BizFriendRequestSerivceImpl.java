package top.yinjinbiao.video.biz.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.yinjinbiao.video.admin.service.SysUserService;
import top.yinjinbiao.video.biz.chat.mapper.BizFriendRequestMapper;
import top.yinjinbiao.video.biz.chat.mapper.BizFriendshipMapper;
import top.yinjinbiao.video.biz.chat.service.BizFriendRequestService;
import top.yinjinbiao.video.common.enums.ChatFriendRequestEnum;
import top.yinjinbiao.video.common.enums.ChatRequestOperatorEnum;
import top.yinjinbiao.video.domain.BizFriendRequest;
import top.yinjinbiao.video.domain.BizFriendship;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.domain.vo.SysUserVO;

@Service
public class BizFriendRequestSerivceImpl implements BizFriendRequestService {
	
	@Autowired
	private SysUserService SysUserService;
	
	@Autowired
	private BizFriendshipMapper bizFriendshipMapper;

	@Autowired
	private BizFriendRequestMapper bizFriendRequestMapper;
	

	@Override
	public List<SysUserVO> queryFriendRequests(Long acceptUserId) {
		return bizFriendRequestMapper.findByAcceptUserId(acceptUserId);
	}
	
	@Override
	@Transactional
	public int saveFriendRequest(Long myUserId, Long friendUserId) {		
		SysUser friendUser = SysUserService.findByUserId(friendUserId);
		if(friendUser==null){
			// 如果用户不存在，返回用户不存在
			return ChatFriendRequestEnum.NOT_FOUND.value();			
		}else{
			BizFriendship friendship = bizFriendshipMapper.findByMyUserIdAndFriendUserId(myUserId,friendUserId);
			// 如果当前发送的好友申请，已经是我的好友
			if(friendship!=null){
				return ChatFriendRequestEnum.ADDED.value();
			}else{
				// 还不是我的好友，但是已经发送过申请
				BizFriendRequest oldFriendRequest = bizFriendRequestMapper.findBySendUserIdAndAcceptUserId(myUserId,friendUserId);
				if(oldFriendRequest!=null){
					return ChatFriendRequestEnum.OK.value();
				}else{
					BizFriendRequest addFriendRequest = new BizFriendRequest(null, myUserId, friendUserId);
					bizFriendRequestMapper.insert(addFriendRequest);
					return ChatFriendRequestEnum.OK.value();					
				}
			}			
		}
	}

	@Override
	@Transactional
	public List<SysUserVO> operateFriendRequest(Long myUserId, Long friendUserId, Integer operateType) {
		//首先，查询别人是不是想加你。如果不想加你，你想操作就是想屁吃。
		BizFriendRequest oldFriendRequest = bizFriendRequestMapper.findBySendUserIdAndAcceptUserId(friendUserId, myUserId);
		if(oldFriendRequest!=null){
			if(ChatRequestOperatorEnum.PASS.value() == operateType){
				//既然是你主动加我的，那我就答应了。
				//我中有你，你中有我
				BizFriendship myFriendship = new BizFriendship(null, myUserId, friendUserId);
				BizFriendship herFriendship = new BizFriendship(null, friendUserId, myUserId);
				bizFriendshipMapper.insert(myFriendship);	
				bizFriendshipMapper.insert(herFriendship);
				//好友加完了，删除申请吧
				bizFriendRequestMapper.deleteByPrimaryKey(oldFriendRequest.getId());				
			}else{
				//果断拒绝她
				bizFriendRequestMapper.deleteByPrimaryKey(oldFriendRequest.getId());	
			}
		}
		List<SysUserVO> myFriendList = bizFriendshipMapper.findMyFriendsById(myUserId);
		return myFriendList;
	}


}

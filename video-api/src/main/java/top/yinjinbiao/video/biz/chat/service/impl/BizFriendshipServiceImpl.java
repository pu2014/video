package top.yinjinbiao.video.biz.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yinjinbiao.video.biz.chat.mapper.BizFriendshipMapper;
import top.yinjinbiao.video.biz.chat.service.BizFriendshipService;
import top.yinjinbiao.video.domain.vo.SysUserVO;

@Service
public class BizFriendshipServiceImpl implements BizFriendshipService {
	
	@Autowired
	private BizFriendshipMapper bizFriendshipMapper;
	
	public List<SysUserVO> findMyFriendsById(Long id){
		return bizFriendshipMapper.findMyFriendsById(id);
	}

}

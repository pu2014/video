package top.yinjinbiao.video.biz.chat.service;

import java.util.List;

import top.yinjinbiao.video.domain.SysUser;

public interface BizFriendshipService {
	
	List<SysUser> findMyFriendsById(Long id);

}

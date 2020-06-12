package top.yinjinbiao.video.biz.chat.service;

import java.util.List;

import top.yinjinbiao.video.domain.vo.SysUserVO;

public interface BizFriendshipService {
	
	/**
	 * 根据我的userId查询我的好友
	 * @param id
	 * @return
	 */
	List<SysUserVO> findMyFriendsById(Long myUserId);

}

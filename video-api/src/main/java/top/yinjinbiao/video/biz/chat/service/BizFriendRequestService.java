package top.yinjinbiao.video.biz.chat.service;

import java.util.List;

import top.yinjinbiao.video.domain.BizFriendRequest;

public interface BizFriendRequestService {
	
	/**
	 * 查询好友申请
	 * @param acceptUserId
	 * @return
	 */
	List<BizFriendRequest> queryFriendRequests(Long acceptUserId);

}

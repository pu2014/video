package top.yinjinbiao.video.biz.chat.service;

import java.util.List;

import top.yinjinbiao.video.domain.vo.SysUserVO;

public interface BizFriendRequestService {
	
	/**
	 * 查询好友申请
	 * @param acceptUserId
	 * @return
	 */
	List<SysUserVO> queryFriendRequests(Long acceptUserId);

	/**
	 * 发送好友申请
	 * @param myUserId
	 * @param friendUserId
	 * @return
	 */
	int saveFriendRequest(Long myUserId, Long friendUserId);

	/**
	 * 接受或拒绝好友申请，并返回新的好友列表
	 * @param myUserId
	 * @param friendUserId
	 * @param operateType
	 * @return
	 */
	List<SysUserVO> operateFriendRequest(Long myUserId, Long friendUserId, int operateType);

}

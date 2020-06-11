package top.yinjinbiao.video.biz.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yinjinbiao.video.biz.chat.mapper.BizFriendRequestMapper;
import top.yinjinbiao.video.biz.chat.service.BizFriendRequestService;
import top.yinjinbiao.video.domain.BizFriendRequest;

@Service
public class BizFriendRequestSerivceImpl implements BizFriendRequestService {
	
	@Autowired
	private BizFriendRequestMapper bizFriendRequestMapper;

	@Override
	public List<BizFriendRequest> queryFriendRequests(Long acceptUserId) {
		return bizFriendRequestMapper.selectByAcceptUserId(acceptUserId);
	}

}

package top.yinjinbiao.video.biz.chat.mapper;

import java.util.List;

import top.yinjinbiao.video.domain.BizFriendRequest;

public interface BizFriendRequestMapper {

    int deleteByPrimaryKey(Long id);


    int insert(BizFriendRequest record);


    int insertSelective(BizFriendRequest record);


    BizFriendRequest selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(BizFriendRequest record);


    int updateByPrimaryKey(BizFriendRequest record);


	List<BizFriendRequest> selectByAcceptUserId(Long acceptUserId);
}
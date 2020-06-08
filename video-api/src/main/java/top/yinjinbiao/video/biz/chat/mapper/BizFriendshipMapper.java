package top.yinjinbiao.video.biz.chat.mapper;

import top.yinjinbiao.video.domain.BizFriendship;

public interface BizFriendshipMapper {

    int deleteByPrimaryKey(Long id);


    int insert(BizFriendship record);


    int insertSelective(BizFriendship record);


    BizFriendship selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(BizFriendship record);


    int updateByPrimaryKey(BizFriendship record);
}
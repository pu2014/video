package top.yinjinbiao.video.biz.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yinjinbiao.video.domain.BizFriendRequest;
import top.yinjinbiao.video.domain.vo.SysUserVO;

public interface BizFriendRequestMapper {

    int deleteByPrimaryKey(Long id);


    int insert(BizFriendRequest record);


    int insertSelective(BizFriendRequest record);


    BizFriendRequest selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(BizFriendRequest record);


    int updateByPrimaryKey(BizFriendRequest record);


	List<SysUserVO> findByAcceptUserId(Long acceptUserId);


	BizFriendRequest findBySendUserIdAndAcceptUserId(@Param("sendUserId")Long sendUserId,@Param("acceptUserId") Long acceptUserId);
}
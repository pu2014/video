package top.yinjinbiao.video.biz.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yinjinbiao.video.domain.BizFriendship;
import top.yinjinbiao.video.domain.vo.SysUserVO;

public interface BizFriendshipMapper {

    int deleteByPrimaryKey(Long id);


    int insert(BizFriendship record);


    int insertSelective(BizFriendship record);


    BizFriendship selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(BizFriendship record);


    int updateByPrimaryKey(BizFriendship record);


	List<SysUserVO> findMyFriendsById(Long id);


	BizFriendship findByMyUserIdAndFriendUserId(@Param("myUserId")Long myUserId,@Param("friendUserId") Long friendUserId);
}
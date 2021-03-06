package top.yinjinbiao.video.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;

public interface SysUserMapper {
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Jun 16 15:59:10 CST 2020
     */
    int updateByPrimaryKeySelective(SysUser record);
	
	SysUser selectByPrimaryKey(Long id);

    List<SysPermission> listByUsername(@Param("username") String username);

    SysUser findByUsername(@Param("username") String username);
    
    
}
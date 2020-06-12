package top.yinjinbiao.video.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;

public interface SysUserMapper {
	
	SysUser selectByPrimaryKey(Long id);

    List<SysPermission> listByUsername(@Param("username") String username);

    SysUser findByUsername(@Param("username") String username);
}
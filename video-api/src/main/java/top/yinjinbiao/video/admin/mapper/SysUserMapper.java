package top.yinjinbiao.video.admin.mapper;

import org.apache.ibatis.annotations.Param;
import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;

import java.util.List;

public interface SysUserMapper {

    List<SysPermission> listByLoginname(@Param("loginname") String loginname);

    SysUser findByLoginname(@Param("loginname") String loginname);
}
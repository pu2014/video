package top.yinjinbiao.video.admin.mapper;

import top.yinjinbiao.video.admin.domain.SysPermission;
import top.yinjinbiao.video.admin.domain.SysUser;
import top.yinjinbiao.video.common.mapper.MyMapper;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {


    List<SysPermission> listByLoginname(String loginname);
}
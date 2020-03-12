package top.yinjinbiao.video.admin.mapper;

import top.yinjinbiao.video.admin.domain.SysPermission;
import top.yinjinbiao.video.common.mapper.MyMapper;

import java.util.List;

public interface SysPermissionMapper extends MyMapper<SysPermission> {


    List<SysPermission> listByLoginname(String loginname);
}
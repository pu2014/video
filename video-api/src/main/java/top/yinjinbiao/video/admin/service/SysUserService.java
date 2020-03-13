package top.yinjinbiao.video.admin.service;

import top.yinjinbiao.video.admin.domain.SysPermission;
import top.yinjinbiao.video.admin.domain.SysUser;
import top.yinjinbiao.video.common.service.BaseService;

import java.util.List;

public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据登录名（账号）查询所有授权
     * @param loginname
     * @return
     */
    List<SysPermission> listByLoginname(String loginname);
}
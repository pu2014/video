package top.yinjinbiao.video.admin.service;

import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 根据登录名（账号）查询所有授权
     * @param loginname
     * @return
     */
    List<SysPermission> listByLoginname(String loginname);

    /**
     * 根据登录账号查询用户
     * @param loginname
     * @return
     */
    SysUser findByLoginname(String loginname);
}
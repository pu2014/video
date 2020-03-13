package top.yinjinbiao.video.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yinjinbiao.video.admin.domain.SysPermission;
import top.yinjinbiao.video.admin.domain.SysUser;
import top.yinjinbiao.video.admin.mapper.SysUserMapper;
import top.yinjinbiao.video.admin.service.SysUserService;
import top.yinjinbiao.video.common.service.impl.AbstractBaseServiceImpl;

import java.util.List;

@Service
public class SysUserServiceImpl extends AbstractBaseServiceImpl<SysUser, SysUserMapper> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysPermission> listByLoginname(String loginname) {
        return sysUserMapper.listByLoginname(loginname);
    }
}

package top.yinjinbiao.video.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.admin.mapper.SysUserMapper;
import top.yinjinbiao.video.admin.service.SysUserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysPermission> listByLoginname(String loginname) {
        return sysUserMapper.listByLoginname(loginname);
    }

    @Override
    public SysUser findByLoginname(String loginname) {
        return sysUserMapper.findByLoginname(loginname);
    }
}

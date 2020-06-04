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

    public List<SysPermission> listByUsername(String listByUsername) {
        return sysUserMapper.listByUsername(listByUsername);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }
}

package top.yinjinbiao.video.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yinjinbiao.video.admin.domain.SysPermission;
import top.yinjinbiao.video.admin.mapper.SysPermissionMapper;
import top.yinjinbiao.video.admin.service.SysPermissionService;
import top.yinjinbiao.video.common.service.impl.AbstractBaseServiceImpl;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends AbstractBaseServiceImpl<SysPermission, SysPermissionMapper> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    public List<SysPermission> listByLoginname(String loginname) {
        return sysPermissionMapper.listByLoginname(loginname);
    }
}

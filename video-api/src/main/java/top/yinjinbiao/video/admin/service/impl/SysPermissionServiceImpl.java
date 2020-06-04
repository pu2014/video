package top.yinjinbiao.video.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.yinjinbiao.video.admin.mapper.SysPermissionMapper;
import top.yinjinbiao.video.admin.service.SysPermissionService;
import top.yinjinbiao.video.domain.SysPermission;

@Service
@Transactional(readOnly = true)
public class SysPermissionServiceImpl implements SysPermissionService {
	
	@Autowired
	private SysPermissionMapper mapper;

	@Override
	public List<SysPermission> list() {
		return mapper.list();
	}

}

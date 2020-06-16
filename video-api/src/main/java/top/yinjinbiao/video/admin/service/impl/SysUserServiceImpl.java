package top.yinjinbiao.video.admin.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import top.yinjinbiao.video.admin.mapper.SysUserMapper;
import top.yinjinbiao.video.admin.service.SysUserService;
import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.domain.vo.SysUserVO;
import top.yinjinbiao.video.upload.service.UploadService;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private UploadService uploadService;

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysPermission> listByUsername(String listByUsername) {
        return sysUserMapper.listByUsername(listByUsername);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

	@Override
	public SysUser findByUserId(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public SysUserVO uploadFaceImg(Long id, MultipartFile file) {
		String faceImgUrl = null;
		try {
			faceImgUrl = uploadService.upload(file.getOriginalFilename(), file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		sysUser.setFaceImg(faceImgUrl);
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		return new SysUserVO(sysUser.getId(),sysUser.getNickname(),sysUser.getUsername(),faceImgUrl);
	}
}

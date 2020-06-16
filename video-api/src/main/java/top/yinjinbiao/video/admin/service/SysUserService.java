package top.yinjinbiao.video.admin.service;

import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.domain.vo.SysUserVO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface SysUserService {

    /**
     * 根据登录名（账号）查询所有授权
     * @param username
     * @return
     */
    List<SysPermission> listByUsername(String username);

    /**
     * 根据登录账号查询用户
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
    
    /**
     * 根据id查询用户
     * @return
     */
    SysUser findByUserId(Long id);

    /**
     * 上传头像
     * @param file
     */
    SysUserVO uploadFaceImg(Long id,MultipartFile file);
}
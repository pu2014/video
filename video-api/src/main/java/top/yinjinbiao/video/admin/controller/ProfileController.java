package top.yinjinbiao.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.yinjinbiao.video.admin.service.SysUserService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.common.util.UserLocalUtil;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.domain.vo.SysUserVO;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	public SysUserService sysUserService;

	/**
	 * 获取当前登陆人信息
	 * @return
	 */
    @GetMapping("/myinfo")
    public ResponseResult<SysUser> info(){
        return new ResponseResult<SysUser>(HttpStatus.OK.value(),"成功",sysUserService.findByUsername(UserLocalUtil.getCurrentUsername()));
    }
    
    /**
     * 上传头像，base64位编码字符串
     * @return
     */
    @PostMapping("/uploadFaceImg")
    public ResponseResult<SysUserVO> uploadFaceImg(MultipartFile file){
    	Long userId = UserLocalUtil.getCurrentUserId();
    	SysUserVO user = sysUserService.uploadFaceImg(userId,file);
		return new ResponseResult<>(HttpStatus.OK.value(),"上传成功",user);
    }

}

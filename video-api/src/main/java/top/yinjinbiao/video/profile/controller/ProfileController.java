package top.yinjinbiao.video.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinjinbiao.video.admin.domain.SysUser;
import top.yinjinbiao.video.common.dto.ResponseResult;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/info")
    public ResponseResult<String> info(){
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return new ResponseResult<String>(HttpStatus.OK.value(),"成功",name);
    }

}

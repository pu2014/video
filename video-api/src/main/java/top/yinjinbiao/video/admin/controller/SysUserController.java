package top.yinjinbiao.video.admin.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinjinbiao.video.admin.domain.SysUser;
import top.yinjinbiao.video.admin.service.SysUserService;

@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户注册", notes = "以实体类为参数")
    @PostMapping("/save")
    public int save(@ApiParam(name = "sysUser", value = "用户模型") SysUser sysUser){
        return sysUserService.save(sysUser);
    }

}

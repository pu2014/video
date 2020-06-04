package top.yinjinbiao.video.admin.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinjinbiao.video.admin.service.SysUserService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.domain.SysUser;

@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户查询", notes = "以登录名为参数")
    @GetMapping("/{loginname}")
    public ResponseResult<SysUser> findByLoginname(@ApiParam(name = "loginname", value = "登录名") @PathVariable(value="loginname",required=true) String loginname){
        return new ResponseResult<SysUser>(HttpStatus.OK.value(),"查询成功",sysUserService.findByUsername(loginname));
    }

}

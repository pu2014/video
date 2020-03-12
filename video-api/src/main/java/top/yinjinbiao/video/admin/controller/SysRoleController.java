package top.yinjinbiao.video.admin.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinjinbiao.video.admin.domain.SysRole;
import top.yinjinbiao.video.admin.service.SysRoleService;

@RestController
@RequestMapping("/sysrole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "用户注册", notes = "以实体类为参数")
    @PostMapping("/save")
    public int save(@ApiParam(name = "sysRole", value = "用户模型") SysRole sysRole){
        return sysRoleService.save(sysRole);
    }

}

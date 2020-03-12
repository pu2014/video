package top.yinjinbiao.video.sso.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.yinjinbiao.video.admin.domain.SysPermission;
import top.yinjinbiao.video.admin.domain.SysUser;
import top.yinjinbiao.video.admin.service.SysPermissionService;
import top.yinjinbiao.video.admin.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 根据用户名查找用户，如果用户不存在抛出异常。
     * @param loginname
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String loginname) throws UsernameNotFoundException {
        /*SysUser sysUser = new SysUser();
        example.setLoginname(loginname);
        SysUser sysUser = sysUserService.getOne(example);
        if(sysUser == null){
            throw new UsernameNotFoundException(loginname);
        }
        SysPermission sysPermission = new SysPermission();
        sysPermission.set
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        // 根据账号查询权限列表
        List<SysPermission> sysPermissionList = sysPermissionService.findByLoginname(loginname);
        for (SysPermission sysPermission : sysPermissionList) {
            authorities.add(new SimpleGrantedAuthority(sysPermission.getCode()));
        }
        sysUser.setAuthorities(authorities);
        return sysUser;*/
        return null;
    }
}


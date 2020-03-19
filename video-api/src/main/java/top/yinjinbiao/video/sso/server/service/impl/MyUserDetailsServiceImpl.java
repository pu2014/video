package top.yinjinbiao.video.sso.server.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.yinjinbiao.video.domain.SysPermission;
import top.yinjinbiao.video.domain.SysUser;
import top.yinjinbiao.video.admin.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    /**
     * TODO 跨包调用
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户名查找用户，如果用户不存在抛出异常。
     * @param loginname
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String loginname) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByLoginname(loginname);
        if(sysUser == null){
            throw new UsernameNotFoundException(loginname);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        // 根据账号查询权限列表
        List<SysPermission> sysPermissionList = sysUserService.listByLoginname(loginname);
        for (SysPermission sysPermission : sysPermissionList) {
            authorities.add(new SimpleGrantedAuthority(sysPermission.getCode()));
        }
        return new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
    }
}


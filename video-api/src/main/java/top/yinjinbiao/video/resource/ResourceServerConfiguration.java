package top.yinjinbiao.video.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import top.yinjinbiao.video.admin.service.SysPermissionService;
import top.yinjinbiao.video.domain.SysPermission;

/**
 * 客户端配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private SysPermissionService sysPermissionService;

    @Override
    public void configure(HttpSecurity http) throws Exception{
    	//基础配置    	
        http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 调整为让 Spring Security 不创建和使用 session
    	
        //从数据库中读取配置的api路径，动态拦截。
    	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
    	List<SysPermission> list = sysPermissionService.list();
    	//这里是配置所有拦截，拦截的路径在sys_permission表中
    	for (SysPermission sysPermission : list) {
    		authorizeRequests.antMatchers(sysPermission.getUrl()).hasAnyAuthority(sysPermission.getCode());
		}
    	authorizeRequests.anyRequest().permitAll()//其他没有限定的请求，允许随意访问
        .and().anonymous();//对于没有配置权限的其他请求允许匿名访问
    }

}


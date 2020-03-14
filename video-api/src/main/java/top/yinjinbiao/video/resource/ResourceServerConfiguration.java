package top.yinjinbiao.video.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 客户端配置
 */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true ,jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 以下为配置所需保护的资源路径及权限，需要与认证服务器配置的授权部分对应
                .antMatchers("/profile/**").authenticated()
                //.antMatchers("/").hasAuthority("SystemContent")
                //.antMatchers("/view/**").hasAuthority("SystemContentView")
                //.antMatchers("/insert/**").hasAuthority("SystemContentInsert")
                //.antMatchers("/update/**").hasAuthority("SystemContentUpdate")
                //.antMatchers("/delete/**").hasAuthority("SystemContentDelete")
                .anyRequest().permitAll()//其他没有限定的请求，允许随意访问
                .and().anonymous();//对于没有配置权限的其他请求允许匿名访问
    }

}


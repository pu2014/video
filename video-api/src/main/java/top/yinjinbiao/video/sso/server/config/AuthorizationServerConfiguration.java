package top.yinjinbiao.video.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * 配置授权服务器
 *
 * @author yin.jinbiao
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * token 的存储方式
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new RedisTokenStore(connectionFactory);
    }

    /**
     * 使用 jdbc 的方式查询客户端信息
     * @return
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource());
    }

    /**
     * 配置ClientDetailsService
     * 注意，除非你在下面的configure(AuthorizationServerEndpointsConfigurer)
     * 中指定了一个AuthenticationManager，否则密码授权方式不可用。 至少配置一个client，否则服务器将不会启动。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //jdbc模式
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 密码模式需要开启
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);// add get method
        endpoints.authenticationManager(authenticationManager);
        // 存储token
        endpoints.tokenStore(tokenStore());
    }

}


package top.yinjinbiao.video.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
@PropertySource("classpath:secret/sso.properties")
@Data
@Component
public class OSSProperties {
    private String accessKeyId;
    private String accessKeySecret;
}

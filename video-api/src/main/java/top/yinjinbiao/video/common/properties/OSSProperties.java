package top.yinjinbiao.video.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "oss.secret", ignoreUnknownFields = false)
@PropertySource("classpath:secret/oss.properties")
@Data
public class OSSProperties {
    private String accessKeyId;
    private String accessKeySecret;
}

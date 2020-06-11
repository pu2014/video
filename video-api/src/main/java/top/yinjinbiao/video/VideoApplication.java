package top.yinjinbiao.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import top.yinjinbiao.video.common.util.SpringUtil;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"top.yinjinbiao.video.admin.mapper","top.yinjinbiao.video.upload.mapper", "top.yinjinbiao.video.biz.chat.mapper"})
class VideoApplication {
	
	@Bean
	public SpringUtil getSpringUtil() {
		return new SpringUtil();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}

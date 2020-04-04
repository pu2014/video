package top.yinjinbiao.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"top.yinjinbiao.video","org.flowable.ui.modeler","org.flowable.ui.common.repository","org.flowable.ui.common.tenant","org.flowable.ui.common.service"})
@MapperScan(basePackages = {"top.yinjinbiao.video.admin.mapper","top.yinjinbiao.video.upload.mapper", "top.yinjinbiao.video.biz.article.mapper"})
class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}

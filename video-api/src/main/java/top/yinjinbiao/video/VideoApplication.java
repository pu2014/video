package top.yinjinbiao.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"org.flowable.ui.modeler","org.flowable.ui.common","top.yinjinbiao.video.admin.mapper","top.yinjinbiao.video.upload.mapper", "top.yinjinbiao.video.biz.article.mapper"})
class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}

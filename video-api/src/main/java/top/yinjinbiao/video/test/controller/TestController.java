package top.yinjinbiao.video.test.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinjinbiao.video.test.domain.Test;
import top.yinjinbiao.video.test.serivce.TestSerivce;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestSerivce testSerivce;

    @ApiOperation(value = "用户注册", notes = "以实体类为参数")
    @PostMapping("/save")
    public int save(@ApiParam(name = "test", value = "test模型")Test test){
        return testSerivce.save(test);
    }

    @ApiOperation(value = "用户查询", notes = "以实体类为参数")
    @GetMapping("/selectOne")
    public Test selectOne(@ApiParam(name = "test", value = "test模型")Test test){
        return testSerivce.selectOne(test);
    }
}

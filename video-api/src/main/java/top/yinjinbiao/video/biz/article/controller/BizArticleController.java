package top.yinjinbiao.video.biz.article.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;

import top.yinjinbiao.video.biz.article.service.BizArticleService;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.domain.BizArticle;

@RestController
@RequestMapping("/article")
public class BizArticleController {

    @Autowired
    private BizArticleService bizArticleService;

    @ApiOperation(value = "保存", notes = "以article实体为参数")
    @PostMapping("/save")
    public ResponseResult<Integer> save(@ApiParam(name = "bizArticle", value = "article实体") BizArticle bizArticle){
        return new ResponseResult<Integer>(HttpStatus.OK.value(),"操作成功", bizArticleService.insert(bizArticle));
    }

    @ApiOperation(value = "分页查询", notes = "以article实体为参数")
    @GetMapping(value = "/page/{pageNumber}/{pageSize}")
    public ResponseResult<PageInfo<BizArticle>> page(@ApiParam(name = "pageNumber", value = "页码", required=true) @PathVariable("pageNumber") int pageNumber,
                               @ApiParam(name = "pageSize", value = "每页显示多少条", required = true) @PathVariable("pageSize") int pageSize,
                               @ApiParam(name = "bizArticle", value = "article实体") BizArticle bizArticle){
        return new ResponseResult<PageInfo<BizArticle>>(HttpStatus.OK.value(),"查询成功", bizArticleService.pageSelective(pageNumber,pageSize,bizArticle));
    }

}

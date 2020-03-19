package top.yinjinbiao.video.upload.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.yinjinbiao.video.common.dto.ResponseResult;
import top.yinjinbiao.video.upload.service.UploadService;

import java.io.IOException;

@RestController
@RequestMapping("/oss")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file) throws IOException {
        return new ResponseResult(HttpStatus.OK.value(),"上传成功",uploadService.upload(file.getOriginalFilename(),file.getBytes()));
    }

    @PostMapping("/delete")
    public ResponseResult delete(String key) throws IOException {
        return new ResponseResult(HttpStatus.OK.value(),"删除成功",uploadService.delete(key));
    }


}

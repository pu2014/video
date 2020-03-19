package top.yinjinbiao.video.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.upload.service.UploadService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传-OSS 服务
 */
@Service
@Transactional(readOnly = true)
public class OSSUploadServiceImpl implements UploadService {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.bucket-endpoint}")
    private String bucketEndpoint;

    @Value("${oss.secret.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.secret.accessKeySecret}")
    private String accessKeySecret;

    @Override
    @Transactional(readOnly = false)
    public String upload(String originalFilename, byte[] data) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = uuid + extName;

        InputStream is = new ByteArrayInputStream(data);
        ossClient.putObject(bucketName, key, is);
        ossClient.shutdown();

        // TODO 将文件信息保存至 sys_file 表

        return bucketEndpoint + key;
    }

    @Override
    @Transactional(readOnly = false)
    public String delete(String key) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, key);
        ossClient.shutdown();

        // TODO 删除 sys_file 表中信息

        return key;
    }
}

package top.yinjinbiao.video.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.domain.SysFile;
import top.yinjinbiao.video.upload.mapper.SysFileMapper;
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


    // oss 参数信息
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


    @Autowired
    private SysFileMapper sysFileMapper;

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

        // 上传文件信息保存到业务表
        String ossDownloadUrl = bucketEndpoint + key;
        SysFile sysFile = new SysFile(null,originalFilename, key, ossDownloadUrl);
        sysFileMapper.insert(sysFile);
        return ossDownloadUrl;
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String key) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, key);
        ossClient.shutdown();

        // 删除业务表中的文件记录
        return sysFileMapper.deleteByKey(key);
    }
}

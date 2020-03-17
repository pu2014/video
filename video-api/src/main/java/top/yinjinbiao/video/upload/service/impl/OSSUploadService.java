package top.yinjinbiao.video.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.BucketInfo;
import com.google.common.annotations.Beta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yinjinbiao.video.upload.service.UploadService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class OSSUploadService implements UploadService {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Autowired
    private String accessKeyId;

    @Autowired
    private String accessKeySecret;

    @Override
    public String upload(String name, byte[] data) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (ossClient.doesBucketExist(bucketName)) {
            System.out.println("您已经创建Bucket：" + bucketName + "。");
        } else {
            System.out.println("您的Bucket不存在，创建Bucket：" + bucketName + "。");
            // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            ossClient.createBucket(bucketName);
        }
        InputStream is = new ByteArrayInputStream(data);
        ossClient.putObject(bucketName, name, is);
        //TODO 回调
        ossClient.shutdown();
        return null;
    }

    @Override
    public String delete(String id) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, id);
        //TODO 回调
        return null;
    }
}

package top.yinjinbiao.video.flowable.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DeploymentDTO {

    private String parentDeploymentId;
    private String id;
    private String name;
    private String category;
    private Date deploymentTime;
    private String key;
    private String derivedFrom;
    private String derivedFromRoot;
    private String tenantId;
    private String engineVersion;


}

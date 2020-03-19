package top.yinjinbiao.video.common.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDomain {

    private Date createTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;

    private Boolean delete;
}

package top.yinjinbiao.video.common.domain;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class BaseDomain {

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_by")
    private Date createBy;

    @Column(name = "update_by")
    private Date updateBy;

    @Column(name = "is_delete")
    private Boolean delete;
}
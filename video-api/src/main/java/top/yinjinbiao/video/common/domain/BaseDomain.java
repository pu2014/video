package top.yinjinbiao.video.common.domain;

import lombok.Data;
import top.yinjinbiao.video.common.annotation.*;

import java.util.Date;

@Data
public class BaseDomain {

    @CreateTime
    protected Date createTime;

    @UpdateTime
    protected Date updateTime;

    @CreateBy
    protected String createBy;

    @UpdateBy
    protected String updateBy;

    @Delete
    protected Boolean delete;
}

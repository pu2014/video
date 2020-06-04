package top.yinjinbiao.video.common.domain;

import lombok.Data;
import top.yinjinbiao.video.common.annotation.*;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDomain implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreateTime
    private Date createTime;

    @UpdateTime
    private Date updateTime;

    @CreateBy
    private String createBy;

    @UpdateBy
    private String updateBy;

    @Delete
    private Boolean delete;
}

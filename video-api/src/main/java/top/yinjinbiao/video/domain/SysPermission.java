package top.yinjinbiao.video.domain;

import lombok.Data;
import top.yinjinbiao.video.common.domain.BaseDomain;

import java.io.Serializable;

/**
* @author yin.jinbiao
* @date 2020-03-12
*/
@Data
public class SysPermission extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;	
    private String name;
    private String url;
    private String code;
    
}
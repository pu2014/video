package top.yinjinbiao.video.domain;

import lombok.Data;
import top.yinjinbiao.video.common.domain.BaseDomain;

import java.io.Serializable;

/**
* @author yin.jinbiao
* @date 2020-03-12
*/
@Data
public class SysRolePermission extends BaseDomain implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;	
	private Long roleId;
	private Long permissionId;
    
}
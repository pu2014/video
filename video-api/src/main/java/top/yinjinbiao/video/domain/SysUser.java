package top.yinjinbiao.video.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.yinjinbiao.video.common.domain.BaseDomain;

/**
* @author yin.jinbiao
* @date 2020-03-12
*/
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SysUser extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;	
    private String username;
    private String loginname;
    private String password;
}
package top.yinjinbiao.video.admin.domain;

import lombok.Data;
import top.yinjinbiao.video.common.domain.BaseDomain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
* @author yin.jinbiao
* @date 2020-03-12
*/
@Data
public class SysUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
    private String username;
    private String loginname;
    private String password;
    
}
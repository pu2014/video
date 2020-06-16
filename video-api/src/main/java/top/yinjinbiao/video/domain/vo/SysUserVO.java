package top.yinjinbiao.video.domain.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 无隐秘信息的SysUser
 * @author yin.jinbiao
 *
 */
@Data
@AllArgsConstructor
public class SysUserVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nickname;
	private String username;
	private String faceImg;
}

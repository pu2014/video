package top.yinjinbiao.video.common.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class VideoUser extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户主键
	 */
	private Long id;
	
	/**
	 * 昵称
	 */
	private String nickname;

	public VideoUser(Long id, String nickname, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
		this.nickname = nickname;
	}
}

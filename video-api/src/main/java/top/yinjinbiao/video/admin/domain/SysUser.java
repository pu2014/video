package top.yinjinbiao.video.admin.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.yinjinbiao.video.common.domain.BaseDomain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;

/**
* @author yin.jinbiao
* @date 2020-03-12
*/
@Data
public class SysUser extends BaseDomain implements UserDetails,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
    private String username;
    private String loginname;
    private String password;

    /**
     * 授权信息
     */
    public Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }
}
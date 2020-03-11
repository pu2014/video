package top.yinjinbiao.video.admin.domain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "sys_user")
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String loginname;

    private String password;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "logic_delete")
    private String logicDelete;

    @Column(name = "account_non_expired")
    private String accountNonExpired;

    @Column(name = "account_non_locked")
    private String accountNonLocked;

    @Column(name = "credentials_non_expired")
    private String credentialsNonExpired;

    private String enabled;

}
package top.yinjinbiao.video.admin.domain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "sys_permission")
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    private String code;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型
     */
    private Integer type;

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

}
package top.yinjinbiao.video.domain;

import lombok.Data;
import top.yinjinbiao.video.common.domain.BaseDomain;

import java.io.Serializable;

@Data
public class SysFile extends BaseDomain implements Serializable {

    private Long id;

    private String name;

    private String url;

}
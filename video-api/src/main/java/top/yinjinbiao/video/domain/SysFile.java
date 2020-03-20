package top.yinjinbiao.video.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.yinjinbiao.video.common.domain.BaseDomain;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SysFile extends BaseDomain {

    private Long id;

    private String name;

    private String key;

    private String url;

}
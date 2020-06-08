package top.yinjinbiao.video.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.yinjinbiao.video.common.domain.BaseDomain;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BizChatRecord extends BaseDomain {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long sendUserId;

    private Long acceptUserId;

    private String msg;

    private Boolean readFlag;

}
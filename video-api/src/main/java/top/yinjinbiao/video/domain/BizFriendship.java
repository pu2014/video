package top.yinjinbiao.video.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.yinjinbiao.video.common.domain.BaseDomain;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BizFriendship extends BaseDomain{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4564539193766039860L;

	private Long id;

    private Long myUserId;

    private Long friendUserId;

}
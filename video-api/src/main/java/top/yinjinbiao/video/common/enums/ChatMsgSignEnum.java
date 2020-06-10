package top.yinjinbiao.video.common.enums;

/**
 * chat模块中的是否签收字段
 * @author yin.jinbiao
 *
 */
public enum ChatMsgSignEnum {
	
	UNSIGN(false),SIGN(true);

    private final Boolean value;
    ChatMsgSignEnum(Boolean value) {
        this.value = value;
    }

    public Boolean value(){
        return value;
    }

}

package top.yinjinbiao.video.common.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author yin.jinbiao
 */
public class UserLocalUtil {

    /**
     * 获取当前登陆人账号，如果未登录返回null。
     * @return
     */
    public static String getCurrentUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        try {
            User user = (User) principal;
            username = user.getUsername();
        }catch (ClassCastException e) {
        	username = null;
        }
        return username;
    }

}

package top.yinjinbiao.video.common.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserLocalUtil {

    /**
     * 获取当前登陆人账号
     * @return
     */
    public static String getCurrentLoginname(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginname;
        try {
            User user = (User) principal;
            loginname = user.getUsername();
        }catch (ClassCastException e) {
            loginname = null;
        }
        return loginname;
    }

}

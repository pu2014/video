package top.yinjinbiao.video.common.util;

import org.springframework.security.core.context.SecurityContextHolder;

import top.yinjinbiao.video.common.dto.VideoUser;

/**
 * @author yin.jinbiao
 */
public class UserLocalUtil {

    /**
     * 获取当前登陆人账号，如果未登录返回null。
     * @return
     */
    public static String getCurrentUsername(){
    	VideoUser currentUserDetails = getCurrentUserDetails();
    	if(currentUserDetails==null){
    		return null;    		
    	}else{
    		return currentUserDetails.getUsername();
    	}
    }
    
    /**
     * 获取当前登陆人id，如果未登录返回null。
     * @return
     */
    public static Long getCurrentUserId(){
    	VideoUser currentUserDetails = getCurrentUserDetails();
    	if(currentUserDetails==null){
    		return null;    		
    	}else{
    		return currentUserDetails.getId();
    	}
    }
    
    /**
     * 获取当前登陆人
     * @return
     */
    public static VideoUser getCurrentUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VideoUser user;
        try{
        	user = (VideoUser) principal;
        }catch (ClassCastException e) {
        	user = null;
        }
        return user;
    }

}

package com.lz.util;

import com.lz.pojo.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCurrentUserName() {
        return getCurrentUser().getUsername();
    }

    public static Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }

    /**
     * 判断当前登录对象是否为销售部员工
     * @return
     */
    public static boolean isMarketing() {
        return SecurityUtils.getSubject().hasRole("marketing");
    }

}

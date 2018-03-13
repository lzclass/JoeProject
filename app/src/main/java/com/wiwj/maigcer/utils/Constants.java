package com.wiwj.maigcer.utils;

/**
*@Author: Joe liuzhaojava@foxmail.com
*@Date: 2018/3/10 0:01
*@Description: 通用常量(注:常量命名必须大写)
*/
public final class Constants {
    private Constants() {
        //限制实例化
    }
    /**是否是debug版本*/
    public static boolean IS_DEBUG = true;
    /** 手机系统版本 */
    public static String OS_VERSION = android.os.Build.VERSION.RELEASE;
    /** 手机型号 */
    public static String PHONE_MODEL = android.os.Build.MODEL;

}

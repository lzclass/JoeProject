package com.wiwj.maigcer.config;

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

    public static  String USER_TOKEN = "token";// 用户token,String型
    public static  String USER_NAME = "userName";// 用户名，String型
    public static  String USER_PHONE = "userMobile";// 用户手机号，String型
    public static  String USER_HEADER_PIC = "userHeaderPic";// 用户头像，String型
    public static  String VALUE_NO_LOGIN = "no_login";// 用户未登入,token的默认值
    public static  String USER_SEX = "sex";// 用户性别，int型
    public static  String USER_ID = "user_id";// 用户id,int型
    public static  String HTTP_URL_BEFORE_LOGIN = "httpurlbeforelogin";// 登录之前从接口请求的域名
    public static  String USER_FIRST_ENTER = "firstEnter";// 用户是否第一次进入，boolean型
    public static  String APP_VERSION = "version";// 应用当前版本
    public static  String APP_SHORT_CUT = "has_shortcut";// lanuncher是否有应用图标，boolean型
    public static  String LONGITUDE = "longitude";// 经度坐标,double值
    public static  String LATITUDE = "latitude";// 纬度坐标，double值
    public static  String ADDRESS = "address";// 坐标相对应的地址名称，String值
    public static  String JPUSH_ALIAS = "jpush_alias";// 当前登录用户的推送别名，String值
    public static  String JPUSH_ALIAS_RESULT = "jpush_alias_result";// 别名设置结果，成功则下次无需进行设置，失败则下次登录时再次进行设置,boolean型
}

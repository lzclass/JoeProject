package com.wiwj.maigcer.collectlog;

/**
 * 
 * 类描述： 
 * @author Ruifeng_Pan
 * @date 2016-12-5
 * @version 
 *
 */
public class LogCollector {

private static final String TAG = LogCollector.class.getName();
	
//	private static String Upload_Url;
//
//	private static Context mContext;
//
//	private static boolean isInit = false;
//
//	private static RequestParams mParams;
//
//	public static void init(Context c , String upload_url , RequestParams params){
//
//		if(c == null){
//			return;
//		}
//
//		if(isInit){
//			return;
//		}
//
//		Upload_Url = upload_url;
//		mContext = c;
//		mParams = params;
//
//		CrashHandler crashHandler = CrashHandler.getInstance(c);
//		crashHandler.init();
//
//		isInit = true;
//
//	}
//
//	public static void upload(boolean isWifiOnly){
//		if(mContext == null || Upload_Url == null){
//			Log.d(TAG, "please check if init() or not");
//			return;
//		}
//		if(!LogCollectorUtility.isNetworkConnected(mContext)){
//			return;
//		}
//		if (!LogHelper.enableDefaultLog) {
//			return;
//		}
//
//		boolean isWifiMode = LogCollectorUtility.isWifiConnected(mContext);
//
//		if(isWifiOnly && !isWifiMode){
//			return;
//		}
//
//		UploadLogManager.getInstance(mContext).uploadLogFile(Upload_Url, mParams);
//	}
//
//	public static void setDebugMode(boolean isDebug){
//		LogHelper.enableDefaultLog = isDebug;
//	}
}

package com.wiwj.maigcer.collectlog.upload;

/**
 * 类描述： 上传log日志manager
 *
 * @author Ruifeng_Pan
 * @date 2016-12-5
 */
public class UploadLogManager {

//    private static final String TAG = UploadLogManager.class.getName();
//
//    private static UploadLogManager sInstance;
//
//    private Context mContext;
//
//    private HandlerThread mHandlerThread;
//
//    private static volatile MyHandler mHandler;
//
//    private volatile Looper mLooper;
//
//    private volatile boolean isRunning = false;
//
//    private String url;
//
//    private RequestParams params;
//    private static final String CHARSET = "UTF-8";
//    private HttpUtils http = new HttpUtils();
//
//    private UploadLogManager(Context c) {
//        mContext = c.getApplicationContext();
//        mHandlerThread = new HandlerThread(TAG + ":HandlerThread");
//        mHandlerThread.start();
//
//    }
//
//    public static synchronized UploadLogManager getInstance(Context c) {
//        if (sInstance == null) {
//            sInstance = new UploadLogManager(c);
//        }
//        return sInstance;
//    }
//
//    public void uploadLogFile(String url, RequestParams params) {
//        this.url = url;
//        this.params = params;
//
//        mLooper = mHandlerThread.getLooper();
//        mHandler = new MyHandler(mLooper);
//        if (mHandlerThread == null) {
//            return;
//        }
//        if (isRunning) {
//            return;
//        }
//        mHandler.sendMessage(mHandler.obtainMessage());
//        isRunning = true;
//    }
//
//    private final class MyHandler extends Handler {
//
//        public MyHandler(Looper looper) {
//            super(looper);
//            // TODO Auto-generated constructor stub
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            File logFile = LogFileStorage.getInstance(mContext)
//                    .getUploadLogFileSD();
//            if (logFile == null || logFile.length() == 0) {
//                isRunning = false;
//                return;
//            }
//            uploadFile(url, params, logFile);
//
//        }
//
//    }
//
//    private void uploadFile(String url, RequestParams params, File logFile) {
//        if (params == null) {
//            params = new RequestParams(CHARSET);
//        }
//        params.addBodyParameter("file", logFile);
//
//        http.send(HttpRequest.HttpMethod.POST, url, params,
//                new RequestCallBack<String>() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onLoading(long total, long current,
//                                          boolean isUploading) {
//                    }
//
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                        isRunning = true;
//                        Utils.toast(responseInfo.result);
//                        // if (condition) {responseInfo 信息上传成功 删除本地存储
//                        //
//                        LogFileStorage.getInstance(mContext)
//                                .deleteUploadLogFileSD();
//                        // }
//                    }
//
//                    @Override
//                    public void onFailure(HttpException error, String msg) {
//                        isRunning = true;
//                    }
//                });
//
//    }

}

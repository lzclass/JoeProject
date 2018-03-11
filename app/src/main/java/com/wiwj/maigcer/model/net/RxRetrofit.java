package com.wiwj.maigcer.model.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.wiwj.maigcer.common.BaseConfig;
import com.wiwj.maigcer.model.bean.FileEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuzhao on 2018/3/5.
 */

public class RxRetrofit {
    private static final int READ_TIME_OUT = 10;
    private static final int WRITE_TIME_OUT = 10;
    private static final int CONNECT_TIME_OUT = 30;//连接超时
    private static Retrofit retrofit;
    private static Context mContext;
    public static void init(Context context) {
        mContext = context;
    }
    public static Retrofit getRetrofit(String baseUrl) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();//使用 gson coverter，统一日期请求格式

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .cache(getCache())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
//              .sslSocketFactory(getSSLSocketFactory(mContext, new int[]{R.raw.tomcat}), trustManager)//设置https访问(验证证书)请把服务器给的证书文件放在R.raw文件夹下
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }
    private static ApiService apiService;
    public static ApiService getDefaultService() {
        if (apiService == null) {
            apiService = RxRetrofit.getRetrofit(BaseConfig.API_HOST).create(ApiService.class);
        }
        return apiService;
    }
    //配置缓存
    public static Cache getCache() {
        File cacheFile = new File(mContext.getExternalCacheDir(), "HttpCache");//缓存地址
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //大小50Mb
        return cache;
    }

    // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
    static X509TrustManager trustManager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                String paramString) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                String paramString) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    //设置https证书
    protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));

                if (is != null) {
                    is.close();
                }
            }
            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 对observable进行统一转换（）用于非文件下载请求
     *
     * @param observable       被订阅者
     * @param observer         订阅者
     * @param lifecycleSubject 生命周期事件发射者
     */
    public static void composeToSubscribe(Observable observable, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        //默认在进入DESTROY状态时发射一个事件来终止网络请求
        composeToSubscribe(observable, observer, LifeCycleEvent.DESTROY, lifecycleSubject);
    }

    /**
     * 对observable进行统一转换（用于非文件下载请求）
     *
     * @param observable       被订阅者
     * @param observer         订阅者
     * @param event            生命周期中的某一个状态，比如传入DESTROY，则表示在进入destroy状态时lifecycleSubject会发射一个事件从而终止请求
     * @param lifecycleSubject 生命周期事件发射者
     */
    public static void composeToSubscribe(Observable observable, Observer observer, LifeCycleEvent event, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        observable.compose(getTransformer(event, lifecycleSubject)).subscribe(observer);
    }


    /**
     * 获取统一转换用的Transformer（用于非文件下载请求）
     *
     * @param event            生命周期中的某一个状态，比如传入DESTROY，则表示在进入destroy状态时
     *                         lifecycleSubject会发射一个事件从而终止请求
     * @param lifecycleSubject 生命周期事件发射者
     */
    public static <T> ObservableTransformer<T, T> getTransformer(final LifeCycleEvent event, final PublishSubject<LifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {

                //当lifecycleObservable发射事件时，终止操作。
                //统一在请求时切入io线程，回调后进入ui线程
                //加入失败重试机制（延迟3秒开始重试，重试3次）
                return upstream
                        .takeUntil(getLifeCycleObservable(event, lifecycleSubject))
                        .retryWhen(new RetryFunction(3, 3))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 对observable进行统一转换（用于文件下载请求）
     *
     * @param observable       被订阅者
     * @param observer         订阅者
     * @param lifecycleSubject 生命周期事件发射者
     * @param file             目标文件，下载的电影将保存到该文件中
     */
    public static void composeToSubscribeForownload(Observable observable, RxFileObserver observer, PublishSubject<LifeCycleEvent> lifecycleSubject, File file) {
        //默认在进入DESTROY状态时发射一个事件来终止网络请求
        composeToSubscribeForDownload(observable, observer, LifeCycleEvent.DESTROY, lifecycleSubject, file);
    }

    /**
     * 对observable进行统一转换（用于文件下载请求）
     *
     * @param observable       被订阅者
     * @param observer         订阅者
     * @param event            生命周期中的某一个状态，比如传入DESTROY，则表示在进入destroy状态时lifecycleSubject会发射一个事件从而终止请求
     * @param lifecycleSubject 生命周期事件发射者
     * @param file             目标文件
     */
    public static void composeToSubscribeForDownload(Observable observable, RxFileObserver observer, LifeCycleEvent event, PublishSubject<LifeCycleEvent> lifecycleSubject, File file) {
        observable.compose(getTransformerForDownload(event, lifecycleSubject, observer, file)).subscribe(observer);
    }

    /**
     * 获取统一转换用的Transformer（用于文件下载请求）
     *
     * @param event            生命周期中的某一个状态，比如传入DESTROY，则表示在进入destroy状态时
     *                         lifecycleSubject会发射一个事件从而终止请求
     * @param lifecycleSubject 生命周期事件发射者
     * @param observer         订阅者
     * @param file             目标文件
     */
    public static <T> ObservableTransformer<T, T> getTransformerForDownload(final LifeCycleEvent event, final PublishSubject<LifeCycleEvent> lifecycleSubject, final RxFileObserver observer, final File file) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                //当lifecycleObservable发射事件时，终止操作。
                //在请求时切入io线程，回调后先在io线程中下载并保存文件到本地，最后再进入ui线程
                return upstream
                        .takeUntil(getLifeCycleObservable(event, lifecycleSubject))
                        .observeOn(Schedulers.io()) //指定doOnNext的操作在io后台线程进行
                        .doOnNext((Consumer<? super T>) new Consumer<ResponseBody>() {

                            //doOnNext里的方法执行完毕，subscriber里的onNext、onError等方法才会执行。
                            @Override
                            public void accept(ResponseBody body) throws Exception {
                                //下载文件，保存到本地
                                boolean isSuccess = downloadAndSave(body, file);
                                //将“文件是否成功保存到本地”的结果传递给订阅者
                                observer.setFileSaveSuccess(isSuccess);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    //下载文件，并保存到目标文件中
    public static boolean downloadAndSave(ResponseBody body, File fileSave) {

        try {
//            File fileSave = FileUtil.generateFile(FileUtil.getFilesDir(), "test.apk");
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[1024 * 4];
                long fileSize = body.contentLength();//文件总长度
                long fileSizeDownloaded = 0;//已下载的文件长度

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(fileSave);

                Logger.d("开始保存到本地");

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;

                    Logger.d("保存进度：" + fileSizeDownloaded + "/" + (fileSize == -1 ? "未知长度" : fileSize));
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取用于控制声明周期的Observable
    public static Observable<LifeCycleEvent> getLifeCycleObservable(final LifeCycleEvent event, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        return lifecycleSubject.filter(new Predicate<LifeCycleEvent>() {
            @Override
            public boolean test(LifeCycleEvent lifeCycleEvent) throws Exception {
                //当生命周期为event状态时，发射事件
                return lifeCycleEvent.equals(event);
            }
        }).take(1);
    }


    //请求失败重试机制
    public static class RetryFunction implements Function<Observable<Throwable>, ObservableSource<?>> {

        private int retryDelaySeconds;//延迟重试的时间
        private int retryCount;//记录当前重试次数
        private int retryCountMax;//最大重试次数

        public RetryFunction(int retryDelaySeconds, int retryCountMax) {
            this.retryDelaySeconds = retryDelaySeconds;
            this.retryCountMax = retryCountMax;
        }

        @Override
        public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
            //方案一：使用zip控制重试次数，重试3次后不再重试（会隐式回调onComplete结束请求，但我需要的是回调onError，所以没采用方案一）
//            return Observable.zip(throwableObservable,Observable.range(1, retryCountMax),new BiFunction<Throwable, Integer, Throwable>() {
//                @Override
//                public Throwable apply(Throwable throwable, Integer integer) throws Exception {
//                    LogUtil.e("ljy",""+integer);
//                    return throwable;
//                }
//            }).flatMap(new Function<Throwable, ObservableSource<?>>() {
//                @Override
//                public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                    if (throwable instanceof UnknownHostException) {
//                        return Observable.error(throwable);
//                    }
//                    return Observable.timer(retryDelaySeconds, TimeUnit.SECONDS);
//                }
//            });


            //方案二：使用全局变量来控制重试次数，重试3次后不再重试，通过代码显式回调onError结束请求
            return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(Throwable throwable) throws Exception {
                    //如果失败的原因是UnknownHostException（DNS解析失败，当前无网络），则没必要重试，直接回调error结束请求即可
                    if (throwable instanceof UnknownHostException) {
                        return Observable.error(throwable);
                    }

                    //没超过最大重试次数的话则进行重试
                    if (++retryCount <= retryCountMax) {
                        //延迟retryDelaySeconds后开始重试
                        return Observable.timer(retryDelaySeconds, TimeUnit.SECONDS);
                    }

                    return Observable.error(throwable);
                }
            });
        }
    }


    /**
     * 生成上传文件用的RequestBody
     *
     * @param fileEntity 文件实体
     * @param mediaType  文件类型
     * @return 请求用的实体
     */
    public static RequestBody fileToPart(FileEntity fileEntity, final MediaType mediaType) {
        return RequestBody.create(mediaType, fileEntity.getFile());
    }

    /**
     * 生成上传文件用的map参数（单个文件）
     *
     * @param fileEntity 文件实体
     * @param mediaType  文件类型
     * @return 请求用的实体
     */
    public static Map<String, RequestBody> fileToPartMap(final FileEntity fileEntity, final MediaType mediaType) {
        List<FileEntity> fileEntities = new ArrayList<>();
        fileEntities.add(fileEntity);
        return filesToPartMap(fileEntities, mediaType);
    }

    /**
     * 生成上传文件用的map参数（多个文件）
     *
     * @param fileEntities 文件实体列表
     * @param mediaType    文件类型
     * @return 请求用的实体
     */
    public static Map<String, RequestBody> filesToPartMap(final List<FileEntity> fileEntities, final MediaType mediaType) {
        final Map<String, RequestBody> bodyMap = new HashMap<>();
        for (FileEntity entity : fileEntities) {
            bodyMap.put(entity.getName() + "\"; filename=\"" + entity.getFile().getName(), RequestBody.create(mediaType, entity.getFile()));
        }
        return bodyMap;
    }

}

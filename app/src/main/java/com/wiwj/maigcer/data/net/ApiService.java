package com.wiwj.maigcer.data.net;

import com.wiwj.maigcer.model.bean.LoginResult;
import com.wiwj.maigcer.net.HttpResult;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by liuzhao on 2018/3/5.
 */

public interface ApiService {

    //请求参数逐个传入
    @FormUrlEncoded
    @POST("请求地址")
    Observable<HttpResult> getInfo(@Field("token") String token, @Field("id") int id);

    //请求参数一次性传入（通过Map来存放参数名和参数值）
    @FormUrlEncoded
    @POST("请求地址")
    Observable<HttpResult> getInfo(@FieldMap Map<String, String> map);

    //上传单个文本和单个文件（如果报错，可以尝试把@Query换成@Field或@Part）
    @Multipart
    @POST("请求地址")
    Observable<HttpResult> upLoadTextAndFile(@Query("textKey") String text,
                                             @Part("fileKey\"; filename=\"test.png") RequestBody fileBody);

    //上传多个文本和多个文件（如果报错，可以尝试把@QueryMap换成@FieldMap或@PartMap）
    @Multipart
    @POST("请求地址")
    Observable<HttpResult> upLoadTextsAndFiles(@QueryMap Map<String, String> textMap,
                                               @PartMap Map<String, RequestBody> fileBodyMap);

    //下载大文件时，请加上@Streaming，否则容易出现IO异常
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String downloadUrl);

    //登录
    @FormUrlEncoded
    @POST("dologin")
    Observable<HttpResult<LoginResult>> getLoginInfo(@Field("principal") String principal,
                                              @Field("password") String password,
                                              @Field("validepassword") String validePassword);
}
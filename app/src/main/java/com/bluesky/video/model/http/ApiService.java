package com.bluesky.video.model.http;

import com.bluesky.video.model.bean.RegistBean;
import com.bluesky.video.model.bean.RegisterData;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by duchao on 2017/5/9.
 */

public interface ApiService {
    String HOST = "http://api.1717goal.com.cn/app/";

    @FormUrlEncoded
    @POST("userreg")
    Flowable<RegisterData> initRegisterInfo(@Field("channel") String channel,
                                            @Field("version") String version,
                                            @Field("versioncode") String versionCode,
                                            @Field("device_id") String deviceId,
                                            @Field("device_name") String deviceName,
                                            @Field("resolution") String resolution,
                                            @Field("os") String os,
                                            @Field("appname") String appName,
                                            @Field("packname") String packName,
                                            @Field("time") String time,
                                            @Field("sign") String sign);
    @FormUrlEncoded
    @POST("userreg")
    Flowable<RegistBean> registUeser(@FieldMap Map<String, String> map);
}

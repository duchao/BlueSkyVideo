package com.bluesky.video.model.http;

import com.bluesky.video.model.bean.RegisterData;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by duchao on 2017/5/6.
 */

public interface ApphApiService {
    String HOST = "http://apph.tywhhc.com:10087/";

    @FormUrlEncoded
    @POST("api.php?s=AppApi0224/initRegist")
    Flowable<RegisterData> initRegisterInfo(@Field("channel") String channel,
                                            @Field("imei") String imei,
                                            @Field("androidid") String androidid,
                                            @Field("mac") String mac,
                                            @Field("bluetooth") String bluetooth,
                                            @Field("mobile") String mobile,
                                            @Field("brand") String brand,
                                            @Field("pkname") String pkname,
                                            @Field("signature") String signature,
                                            @Field("sysversion") String sysversion,
                                            @Field("sp") String sp,
                                            @Field("versioncode") String versioncode,
                                            @Field("leve") String leve);
}

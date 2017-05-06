package com.bluesky.video.model.http;

import com.bluesky.video.model.bean.IsUserRegisterBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by duchao on 2017/5/6.
 */

public interface MvApiService {
    String HOST = "http://114.215.80.28:9000/";

    @FormUrlEncoded
    @POST("mvmanager/device/isUserRegister")
    Flowable<IsUserRegisterBean> getIsUserRegistInfo(@Field("channel") String channel,
                                                     @Field("imei") String imei,
                                                     @Field("androidid") String androidId) ;
}

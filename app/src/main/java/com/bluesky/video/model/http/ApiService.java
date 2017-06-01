package com.bluesky.video.model.http;

import com.bluesky.video.model.bean.PinDaoData;
import com.bluesky.video.model.bean.VideoData;
import com.bluesky.video.model.bean.RegistBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by duchao on 2017/5/9.
 */

public interface ApiService {

    String HOST = "http://api.1717goal.com.cn/app/";
    @GET("userreg")
    Flowable<RegistBean> registUeser(@Query("device_id") String deviceId,
                                     @Query("channel") String channel,
                                     @Query("sign") String sign,
                                     @Query("time") String time);

    @FormUrlEncoded
    @POST("video")
    Flowable<VideoData> getVideData(@Field("videoType") String videoType);

    @FormUrlEncoded
    @POST("pindao")
    Flowable<PinDaoData> getPinDaoData(@Field("parentid") String parentId);
}

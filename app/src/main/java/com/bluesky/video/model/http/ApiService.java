package com.bluesky.video.model.http;

import com.bluesky.video.model.bean.HomeData;
import com.bluesky.video.model.bean.RegistBean;

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
    Flowable<RegistBean> registUeser(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("video")
    Flowable<HomeData> getHomeVideData(@Field("videoType") String videoType);
}

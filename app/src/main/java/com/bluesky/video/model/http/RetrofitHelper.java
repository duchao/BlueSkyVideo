package com.bluesky.video.model.http;

import com.bluesky.video.BuildConfig;
import com.bluesky.video.app.Constants;
import com.bluesky.video.model.bean.VideoData;
import com.bluesky.video.model.bean.RegistBean;
import com.bluesky.video.model.config.SystemInfo;
import com.bluesky.video.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duchao on 2017/5/6.
 */

public class RetrofitHelper {
    private static OkHttpClient sOkHttpClient = null;
    private static ApiService sApiService = null;

    public RetrofitHelper() {
        init();
    }
    private void init() {
        initOkHttp();
        sApiService = getApiService();
    }
    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheIntercepter = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isNetworkAvailable()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isNetworkAvailable()) {
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        }
//        设置统一的请求头部参数
//        builder.addInterceptor(apikey);
        //设置缓存

        //设置缓存
        builder.addNetworkInterceptor(cacheIntercepter);
        builder.addInterceptor(cacheIntercepter);
        builder.cache(cache);

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //错误重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
    }

    private static ApiService getApiService() {
        Retrofit apiRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.HOST)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return apiRetrofit.create(ApiService.class);
    }

    public Flowable<RegistBean> registUser() {
        SystemInfo systemInfo = SystemInfo.getInstance();
        Map<String, String> map = new HashMap<>();
//        map.put("channel", systemInfo.getChannel());
//        map.put("version", systemInfo.getVersionName());
//        map.put("versioncode", String.valueOf(systemInfo.getVersionCode()));
//        map.put("device_id", systemInfo.getIMEI());
//        map.put("device_name", "");
//        map.put("resolution", systemInfo.getResolution());
//        map.put("os", systemInfo.getOSVersion());
//        map.put("appname", systemInfo.getAppName());
//        map.put("packname", systemInfo.getPackageName());
//        map.put("time", systemInfo.getTime());
//        map.put("sign", systemInfo.getSign());
        map.put("device_name", "FRD-AL00");
        map.put("packname", "com.wan.wanplayer");
        map.put("device_id", "863696033021255");
        map.put("os", "7.0");
        map.put("appname", "情涩快播");
        map.put("channel", "default01");
        map.put("sign", systemInfo.getSign());
        map.put("time", systemInfo.getTime());
        map.put("versioncode", "18");
        map.put("version", "2.8.0");
        map.put("resolution", "1080*1794");

        return sApiService.registUeser("863696033021255", "default01", systemInfo.getSign(), systemInfo.getTime());
    }

   public Flowable<VideoData> getVideoData(String videoType) {
       return sApiService.getVideData(videoType);//默认值为1
   }

}

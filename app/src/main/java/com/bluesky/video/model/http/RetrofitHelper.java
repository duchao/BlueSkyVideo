package com.bluesky.video.model.http;

import com.bluesky.video.BuildConfig;
import com.bluesky.video.app.Constants;
import com.bluesky.video.model.bean.IsUserRegisterBean;
import com.bluesky.video.model.bean.RegisterData;
import com.bluesky.video.utils.NetworkUtil;

import java.io.File;
import java.io.IOException;
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

    private static MvApiService sMvApiService = null;

    private static ApphApiService sApphApiService = null;

    public RetrofitHelper() {
        init();
    }
    private void init() {
        initOkHttp();
        sMvApiService = getMvApiService();
        sApphApiService = getApphApiService();
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
                if (!NetworkUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtil.isNetworkConnected()) {
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

    private static MvApiService getMvApiService() {
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(MvApiService.HOST)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return zhihuRetrofit.create(MvApiService.class);
    }

    private static ApphApiService getApphApiService() {
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ApphApiService.HOST)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return zhihuRetrofit.create(ApphApiService.class);
    }

    public Flowable<IsUserRegisterBean> getIsUserRegistInfo() {
        //这里后续要修改成设备名称
        return sMvApiService.getIsUserRegistInfo("1", "1", "1");
    }

    public Flowable<RegisterData> initRegisterInfo() {
        //这里后续要修改成设备名称
        return sApphApiService.initRegisterInfo(
                "a294705",
                "867905027942921",
                "15",
                "68:3e:34:9c:0d:db",
                "68:3E:34:9C:0D:DC",
                "",
                "",
                "com.feiofjoc.xkjoelb",
                "B4:66:CE:B5:A3:BC:6D:36:F8:18:29:B2:BC:27:4A:DA:CB:E9:C0:F4",
                "22",
                "1",
                "2000025",
                "0" );
    }

}

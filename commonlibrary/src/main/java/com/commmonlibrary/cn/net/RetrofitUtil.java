package com.commmonlibrary.cn.net;

import android.content.Context;
import android.util.Log;

import com.commmonlibrary.cn.net.interceptor.CacheNetInterceptor;
import com.commmonlibrary.cn.progress.ProgressListener;
import com.commmonlibrary.cn.progress.ProgressResponseBody;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chawei on 2018/8/22.
 */

public class RetrofitUtil {

    private static final  int REQ_TIMEOUT=10;
    public static Context sContext =null;

    public RetrofitUtil(){}

    public static void init(Context context) {
        sContext = context;
    }


    public static Retrofit getRetrofit(String baseUrl) {
        Retrofit.Builder retrofitBuilder = buildRetrofitBuilder(baseUrl);
        OkHttpClient client = buildOkHttpClient();
        retrofitBuilder.client(client);
        return retrofitBuilder.build();

    }

    private static Retrofit.Builder buildRetrofitBuilder(String baseUrl) {
        return (new Retrofit.Builder()).baseUrl(baseUrl).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(StringConverterFactory.create());
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static OkHttpClient buildOkHttpClient(){
        OkHttpClient.Builder builder = buildOkHttpClientBuilder();
        builder.connectTimeout(REQ_TIMEOUT, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        return client;
    }

    public static OkHttpClient.Builder buildOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new Logger());
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Log.d("ez", "intercept: ---->"+chain.request().url());

                Request original = chain.request();
                Request request = original.newBuilder().header("Accept", "application/json").build();
                Response response = chain.proceed(request);
                return response;
            }
        });
        File cacheFile = new File(sContext.getCacheDir(), sContext.getPackageName());
        Cache cache = new Cache(cacheFile, 104857600L);
        builder.cache(cache);
        builder.addNetworkInterceptor(new CacheNetInterceptor(sContext));
        return builder;
    }

    public static Retrofit getUploadRetrofit(String baseUrl) {
        Retrofit.Builder retrofitBuilder = buildRetrofitBuilder(baseUrl);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.build();
        return retrofitBuilder.build();
    }

    public static Retrofit getDownRetrofit(String baseUrl, final ProgressListener progressListener) {
        Retrofit.Builder retrofitBuilder = buildRetrofitBuilder(baseUrl);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(),progressListener)).build();
            }
        });
        OkHttpClient client = builder.build();
        retrofitBuilder.client(client);
        return retrofitBuilder.build();
    }
}

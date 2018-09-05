package com.commmonlibrary.cn.net.interceptor;

import android.content.Context;
import android.text.TextUtils;

import com.commmonlibrary.cn.R;
import com.commmonlibrary.cn.utils.NetWorkUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chawei on 2018/8/22.
 */

public class CacheNetInterceptor implements Interceptor {

    Context mContext;

    public CacheNetInterceptor(Context context) {
        mContext=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkUtils.isNetworkAvailable(mContext)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            Logger.e(mContext.getString(R.string.no_net),new Object[0]);
        }

        Response originalresponse = chain.proceed(request);

        if (NetWorkUtils.isNetworkAvailable(mContext)) {
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public,max-age=3600";
            }
            return originalresponse.newBuilder().header("Cache-Control",cacheControl).removeHeader("Pragma").build();
        }else{
            return originalresponse.newBuilder().header("Cache-Control","public,only-if-cached,max-stale=2419200").removeHeader("Pragma").build();
        }
    }
}

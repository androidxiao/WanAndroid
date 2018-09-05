package com.commmonlibrary.cn.net;

import android.util.Log;

import com.commmonlibrary.cn.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.commmonlibrary.cn.net.RetrofitUtil.sContext;

/**
 * Created by chawei on 2018/8/26.
 */

public class RequestUtils {

    public static final String BASE_URL = "http://www.wanandroid.com/";

    public static Retrofit get() {
        Retrofit retrofit = RetrofitUtil.getRetrofit(BASE_URL);
        return retrofit;
    }

    public static <T> void toSubscribe(Observable<T> o) {
        o
                .doOnSubscribe(disposable -> {
                    if (mRequestListener != null) {
                        mRequestListener.onStart();
                    }
                    Log.d("ez", "toSubscribe: 显示 loading");
                })
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                    if (mRequestListener != null) {
                        mRequestListener.onSuccess(t);
                    }
                }, e -> {
                    ToastUtil.showShortToast(sContext, e.getMessage());
                    if (mRequestListener != null) {
                        mRequestListener.onError(e);
                    }
                }, () -> {
                    if (mRequestListener != null) {
                        mRequestListener.onEnd();
                    }
                });
    }

    private static IRequestListener mRequestListener;

    public static void setRequestListener(IRequestListener listener) {
        mRequestListener = listener;
    }


    public interface IRequestListener<T> {

        default void onStart(){}

        default void onError(Throwable e){}

        void onSuccess(T t);

        default void onEnd(){}
    }
}

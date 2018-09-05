package com.wanandroid.cn;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.commmonlibrary.cn.net.RetrofitUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by chawei on 2018/8/18.
 */

public class FakeApp extends Application {

    public static boolean IS_DEBUG = com.commmonlibrary.cn.BuildConfig.DEBUG ;
    private static FakeApp mBaseApplication ;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);

        if(IS_DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
            setCustomLog();
        }
        ARouter.init(this);

        RetrofitUtil.init(this);

        Utils.init(this);
    }

    private void setCustomLog(){
        //配置日志类型，正式打包与Debug区分开
        if (com.commmonlibrary.cn.BuildConfig.DEBUG) {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(0)         // (Optional) How many method line to show. Default 2
                    .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                    .tag("wanandroid")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        }

    }

    public static FakeApp getAppContext(){
        return mBaseApplication;
    }
}

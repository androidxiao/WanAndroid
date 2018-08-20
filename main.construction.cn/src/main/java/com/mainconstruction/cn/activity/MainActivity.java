package com.mainconstruction.cn.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.commmonlibrary.cn.base.BaseActivity;
import com.commmonlibrary.cn.router.RouterURLS;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.fragment.MainFragment;
import com.orhanobut.logger.Logger;

/**
 * Created by chawei on 2018/8/18.
 */
@Route(path = RouterURLS.MAIN_MAIN)
public class MainActivity extends BaseActivity{


    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.appmain_ac_main);

        initRootFg();

        setOnClickListener();
    }

    private void initRootFg(){
        loadRootFragment(R.id.fl_container, MainFragment.getInstance(new Bundle()));
    }

    private void setOnClickListener(){


    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

                ARouter.getInstance().build(RouterURLS.USER_LOGIN)
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        Logger.d("onArrival--->"+postcard.getPath());
                    }

                    @Override
                    public void onFound(Postcard postcard) {
                        super.onFound(postcard);
                        Logger.d("onFound: ----->" +postcard.getPath());
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        super.onLost(postcard);
                        Logger.d("onLost: ------>" +postcard.getPath());
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);

                        Logger.d("onInterrupt: ------>" +postcard.getPath());
                    }
                });
    }

}

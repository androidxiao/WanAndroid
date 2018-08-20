package cn.android.wan.com.user_related;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.commmonlibrary.cn.mvp.MvpBaseActivity;
import com.commmonlibrary.cn.router.RouterURLS;

/**
 * Created by chawei on 2018/8/18.
 */
@Route(path = RouterURLS.USER_LOGIN)
public class LoginActivity extends MvpBaseActivity {


    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.user_ac_login);
    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }
}

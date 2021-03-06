package com.mainconstruction.cn.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.mvp.MvpBaseFragment;
import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.fragment.presenterImpl.ProjectTitlePresenterImpl;

/**
 * Created by chawei on 2018/8/19.
 */

public class NavigatorFragment extends MvpBaseFragment {

    public static NavigatorFragment getInstance(String content){
        NavigatorFragment fragmentA=new NavigatorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",content);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }

    @Override
    public void beforeViewData() {

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.appmain_fg_home,container,false);
    }

    @Override
    public void initView(View view,Bundle savedInstanceState) {
        Log.d(TAG, "initView: NavigatorNavigatorNavigatorNavigatorNavigatorNavigator");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected MvpPresenter getPresenter() {
        return new ProjectTitlePresenterImpl();
    }
}

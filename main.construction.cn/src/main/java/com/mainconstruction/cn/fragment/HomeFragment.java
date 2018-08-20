package com.mainconstruction.cn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.mvp.MvpBaseFragment;
import com.mainconstruction.cn.R;

/**
 * Created by chawei on 2018/8/19.
 */

public class HomeFragment extends MvpBaseFragment {


    public static HomeFragment getInstance(String content){
        HomeFragment fragmentA=new HomeFragment();
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
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }
}
package com.commmonlibrary.cn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.widget.CommonLoadingView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chawei on 2018/6/19.
 */

public abstract class BaseFragment extends SupportFragment implements View.OnClickListener{

    public static final String TAG = "ez";
    protected boolean mHasInited;//Fragment 是否初始化完 View
    private CommonLoadingView mLoadingView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHasInited=true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeViewData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = initLayout(inflater, container, savedInstanceState);
        initView(view,savedInstanceState);
        return view;
    }

    /**
     * 在View初始化之前，需要初始化数据的，实现此方法
     */
    public abstract void beforeViewData();

    /**
     * 初化布局文件实现此方法
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public abstract View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    /**
     * 初始化View实现此方法,并设置需要点击的控件的监听
     * @param savedInstanceState
     */
    public abstract void initView(View view,Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        mHasInited=false;
        super.onDestroyView();
    }

    /**
     * 显示加载Loading
     */
    public void showLoadingView() {
        if(mHasInited) {
            if(mLoadingView == null) {
                mLoadingView = new CommonLoadingView(getActivity());
            }
            mLoadingView.show(getActivity().getWindow().getDecorView());
        }
    }

    /**
     * 关闭加载Loading
     */
    public void dismissLoadingView() {
        if(mLoadingView != null && mLoadingView.isShowing()) {
            mLoadingView.dismiss();
            mLoadingView = null;
        }
    }

    /**
     * 初始化SwipeRefreshLayout
     * @param refreshLayout
     */
    protected void initSwipeRefreshLayout(SwipeRefreshLayout refreshLayout, boolean isAutoRefresh){
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        refreshLayout.setProgressViewOffset(true, 0, 50);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //只设置一种颜色
//        refreshLayout.setColorSchemeColors(getResources()
//                .getColor(android.R.color.black));

        // 通过 setEnabled(false) 禁用下拉刷新
        refreshLayout.setEnabled(true);

        //设置手势下拉刷新的监听
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        onRefreshLoadData();
                    }
                }
        );

        if(isAutoRefresh) {
            autoRefresh(refreshLayout);
        }
    }

    /**
     * 自动刷新
     * @param refreshLayout
     */
    private void autoRefresh(SwipeRefreshLayout refreshLayout){
        refreshLayout.measure(0,0);
        refreshLayout.setRefreshing(true);
    }

    protected void onRefreshLoadData(){

    }
}

package com.commmonlibrary.cn.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.commmonlibrary.cn.utils.ToastUtil;
import com.commmonlibrary.cn.widget.CommonLoadingView;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by chawei on 2018/6/20.
 */

public abstract class BaseActivity extends SupportActivity implements View.OnClickListener{

    public static final String TAG = "ez";

    protected boolean mHasInited;//Activity 是否初始化完成
    private CommonLoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeViewData();
        initLayoutView();
    }

    /**
     * 在View初始化之前，需要初始化数据的，实现此方法
     */
    public abstract void beforeViewData();

    /**
     * 实现此方法，必需先加载化布局文件，再并初始化View, 并设置需要点击的控件的监听
     */
    public abstract void initLayoutView();

    /**
     * 请求网络数据实现此方法
     */
    public abstract void requestNetData();

    @Override
    public void onAttachedToWindow() {
        mHasInited  = true;
        requestNetData();
    }

    @Override
    protected void onDestroy() {
        mHasInited=false;
        dismissLoadingView();
        super.onDestroy();
    }

    /**
     * 显示加载Loading
     */
    public void showLoadingView() {
        if(mHasInited) {
            if(mLoadingView == null) {
                mLoadingView = new CommonLoadingView(this);
            }
            mLoadingView.show(getWindow().getDecorView());
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
     * 短时Toast
     * @param text
     */
    public void showShortToast(String text) {
        ToastUtil.showShortToast(this, text);
    }

    /**
     * 长时Toast
     * @param text
     */
    public void showLongToast(String text) {
        ToastUtil.showLongToast(this, text);
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

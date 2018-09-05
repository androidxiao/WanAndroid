package com.mainconstruction.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.commmonlibrary.cn.mvp.MvpBaseFragment;
import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.commmonlibrary.cn.router.Router;
import com.flyco.tablayout.SlidingTabLayout;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.activity.ChannelActivity;
import com.mainconstruction.cn.adapter.HomeTabAdapter;
import com.mainconstruction.cn.fragment.presenter.ProjectTitleContact;
import com.mainconstruction.cn.fragment.presenterImpl.ProjectTitlePresenterImpl;
import com.mainconstruction.cn.model.ProjectTitlePo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chawei on 2018/8/19.
 *
 * 加载的是"项目"的数据
 */

public class HomeFragment extends MvpBaseFragment implements ProjectTitleContact.ProjectTitleMvpView{


    private SlidingTabLayout mStb;
    private List<Fragment> mFragmentList;
    private ViewPager mVp;
    private List<ProjectTitlePo.Data> mData;
    private List<ProjectTitlePo.Data> mAllChannels;
    private ImageView mIvAddChannel;
    private final int SHOW_COUNT=6;


    public static HomeFragment getInstance(String content) {
        HomeFragment fragmentA = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", content);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }

    @Override
    public void beforeViewData() {
        mFragmentList = new ArrayList<>();
        mData = new ArrayList<>();
        mAllChannels = new ArrayList<>();

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appmain_fg_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mStb = view.findViewById(R.id.stb);
        mVp = view.findViewById(R.id.vp);
        mIvAddChannel = view.findViewById(R.id.id_iv_add_channel);



        mIvAddChannel.setOnClickListener(this);
    }

    @Override
    public void initView(View view,Bundle savedInstanceState) {


    }
    @Override
    public void onClick(View v) {

       if(v.getId()==R.id.id_iv_add_channel){
           Router.newIntent(getActivity()).putSerializable("key", (Serializable) mAllChannels).to(ChannelActivity.class).launch();
       }


    }


    @Override
    protected MvpPresenter getPresenter() {
        Log.d(TAG, "getPresenter: Home111-----》");
        return new ProjectTitlePresenterImpl();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.initialize();
    }

    @Override
    public void getProjectTitleData(ProjectTitlePo po) {

        List<ProjectTitlePo.Data> data = po.getData();

        mAllChannels=data;

        Log.d(TAG, "getProjectData: 请求成功了----》"+data.size());

        for(int i=0;i<SHOW_COUNT;i++) {//只显示 6 个，剩下的在列表中
            mFragmentList.add(ProjectFragment.getInstance(data.get(i).getId()));
            mData.add(data.get(i));
        }

        HomeTabAdapter tabAdapter = new HomeTabAdapter(getFragmentManager(),mFragmentList,mData);
        mVp.setAdapter(tabAdapter);
        mStb.setViewPager(mVp);
    }
}

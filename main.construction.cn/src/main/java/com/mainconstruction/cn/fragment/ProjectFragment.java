package com.mainconstruction.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.loadrecyclerview.LoadMoreRecyclerView;
import com.commmonlibrary.cn.mvp.MvpBaseFragment;
import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.commmonlibrary.cn.utils.ToastUtil;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.adapter.ProjectListAdapter;
import com.mainconstruction.cn.fragment.presenter.ProjectContact;
import com.mainconstruction.cn.fragment.presenterImpl.ProjectPresenterImpl;
import com.mainconstruction.cn.model.ProjectPo;

import java.util.List;

/**
 * Created by chawei on 2018/8/19.
 */

public class ProjectFragment extends MvpBaseFragment implements ProjectContact.ProjectMvpView{

    int curPage =1;
    private int mId;
    private LoadMoreRecyclerView mRv;
    private ProjectListAdapter mAdapter;
    private int mPageCount;

    public static ProjectFragment getInstance(int id){
        ProjectFragment fragmentA=new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }


    @Override
    public void beforeViewData() {

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.appmain_fg_project,container,false);
    }

    @Override
    public void initView(View view,Bundle savedInstanceState) {
        Log.d(TAG, "initView: ProjectProjectProjectProjectProject");
        mId = getArguments().getInt("id");

        mRv = view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ProjectListAdapter(getActivity());
        mRv.setAdapter(mAdapter);


        loadMoreRv();
    }

    private void loadMoreRv(){

        mRv.setLoadMoreEnabled(true);

        mRv.setOnLoadListener(new LoadMoreRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {

                if(curPage>mPageCount){
                    ToastUtil.showShortToast(getActivity(),getString(R.string.appmain_no_load_more));
                    return;
                }

                ++curPage;

                loadData();
            }
        });
    }


    private void loadData(){

        mPresenter.initialize(curPage,String.valueOf(mId));

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        loadData();
    }

    @Override
    protected MvpPresenter getPresenter() {
        return new ProjectPresenterImpl();
    }


    @Override
    public void getProjectData(ProjectPo po) {

        mPageCount = po.getData().getPageCount();

        List<ProjectPo.DataBean.DatasBean> datas = po.getData().getDatas();
        if (curPage == 1) {
            mAdapter.setDatas(datas);
        }else{
            mAdapter.addDatas(datas);
        }

        if (curPage == mPageCount) {
            mRv.noNeedToLoadMore();
        }else {
            mRv.loadMoreComplete();
        }

    }
}

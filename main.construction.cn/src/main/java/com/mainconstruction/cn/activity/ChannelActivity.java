package com.mainconstruction.cn.activity;

import android.util.Log;
import android.view.View;

import com.commmonlibrary.cn.base.BaseActivity;
import com.mainconstruction.cn.model.ProjectTitlePo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chawei on 2018/9/4.
 */

public class ChannelActivity extends BaseActivity {


    private List<ProjectTitlePo.Data> mDataList;

    @Override
    public void beforeViewData() {
        Serializable key = getIntent().getSerializableExtra("key");
        mDataList = (List<ProjectTitlePo.Data>) key;
        Log.d(TAG, "beforeViewData: ---->"+mDataList.size());
    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }
}

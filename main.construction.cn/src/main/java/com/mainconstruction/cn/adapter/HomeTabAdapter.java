package com.mainconstruction.cn.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mainconstruction.cn.model.ProjectTitlePo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chawei on 2018/8/27.
 */

public class HomeTabAdapter extends FragmentPagerAdapter {


    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<ProjectTitlePo.Data> mPoList = new ArrayList<>();

    public HomeTabAdapter(FragmentManager fm, List<Fragment> fragments, List<ProjectTitlePo.Data> poList) {
        super(fm);
        mFragmentList.clear();
        mPoList.clear();
        mFragmentList = fragments;
        mPoList = poList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mPoList!=null&&mPoList.size()>0?mPoList.size():0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mPoList.get(position).getName();
    }
}

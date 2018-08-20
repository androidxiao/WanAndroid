package com.mainconstruction.cn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.base.BaseFragment;
import com.commmonlibrary.cn.widget.BottomBar;
import com.commmonlibrary.cn.widget.BottomBarTab;
import com.mainconstruction.cn.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chawei on 2018/8/19.
 */

public class MainFragment extends BaseFragment {

    private SupportFragment[] mFragments = new SupportFragment[4];
    private BottomBar mBottomBar;

    public static MainFragment getInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void beforeViewData() {

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appmain_fg_main, container, false);
        ;
        initBottomBarView(view);
        return view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        HomeFragment homeFragment = findChildFragment(HomeFragment.class);
        if (homeFragment == null) {
            mFragments[0] = HomeFragment.getInstance("");
            mFragments[1] = KnowledgeFragment.getInstance("");
            mFragments[2] = NavigatorFragment.getInstance("");
            mFragments[3] = ProjectFragment.getInstance("");
            loadMultipleRootFragment(R.id.fl_tab_container, 0, mFragments[0], mFragments[1], mFragments[2], mFragments[3]);
        } else {
            mFragments[0] = homeFragment;
            mFragments[1] = findChildFragment(KnowledgeFragment.class);
            mFragments[2] = findChildFragment(NavigatorFragment.class);
            mFragments[3] = findChildFragment(ProjectFragment.class);
        }
    }

    private void initBottomBarView(View view) {
        mBottomBar = view.findViewById(R.id.bottomBar);
        mBottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.appmain_ic_home_selector, "主页"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.appmain_ic_home_selector, "主页"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.appmain_ic_home_selector, "主页"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.appmain_ic_home_selector, "主页"));

        setBottomBarListener();
    }

    private void setBottomBarListener(){
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}

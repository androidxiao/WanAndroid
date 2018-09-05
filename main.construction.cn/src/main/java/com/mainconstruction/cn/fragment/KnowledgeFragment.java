package com.mainconstruction.cn.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.BaseModel;
import com.commmonlibrary.cn.mvp.MvpBaseFragment;
import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.adapter.KnowledgeAdapter;
import com.mainconstruction.cn.fragment.presenterImpl.ProjectTitlePresenterImpl;
import com.mainconstruction.cn.widget.ScrollViewLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chawei on 2018/8/19.
 */

public class KnowledgeFragment extends MvpBaseFragment {

    public static KnowledgeFragment getInstance(String content){
        KnowledgeFragment fragmentA=new KnowledgeFragment();
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
        View view=inflater.inflate(R.layout.appmain_fg_knowledge,container,false);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view,Bundle savedInstanceState) {

        Log.d(TAG, "initView: KnowKnowKnowKnowKnowKnowKnow");

//        IService service1 = RequestUtils.get().create(IService.class);
//        Observable<HomePo> list = service1.getHomeList();
//        RequestUtils.setRequestListener(new RequestUtils.IRequestListener() {
//            @Override
//            public void onSuccess(Object o) {
//
//            }
//
//        });
//        RequestUtils.toSubscribe(list);
    }

    private void initView(View view){


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        // 线性
        recyclerView.setLayoutManager(new ScrollViewLinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        List<KnowledgePo> poList = new ArrayList<>();

        for(int i=0;i<400;i++) {
            KnowledgePo po = new KnowledgePo();
            if (i % 2 == 0) {
                po.setTitle("第一个 Po-->"+i);
                po.setSecond(true);
            }else{
                po.setSecond(false);
                po.setTitle("第二个 Po-->"+i);
            }
            poList.add(po);
        }


        KnowledgeAdapter adapter = new KnowledgeAdapter(getActivity());
        adapter.setDatas(poList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected MvpPresenter getPresenter() {
        Log.d(TAG, "getPresenter: Knowledge-----》");
       return new ProjectTitlePresenterImpl();
    }

    public class KnowledgePo extends BaseModel {
        private String title;
        private boolean isSecond;

        public boolean isSecond() {
            return isSecond;
        }

        public void setSecond(boolean second) {
            isSecond = second;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

package com.mainconstruction.cn.fragment.presenterImpl;

import android.util.Log;

import com.commmonlibrary.cn.mvp.BasePresenter;
import com.commmonlibrary.cn.net.RequestUtils;
import com.mainconstruction.cn.model.ProjectTitlePo;
import com.mainconstruction.cn.net.IService;

import io.reactivex.Observable;

import static com.commmonlibrary.cn.base.BaseFragment.TAG;
import static com.mainconstruction.cn.fragment.presenter.ProjectTitleContact.ProjectTitleMvpPresenter;
import static com.mainconstruction.cn.fragment.presenter.ProjectTitleContact.ProjectTitleMvpView;

/**
 * Created by chawei on 2018/8/26.
 */

public class ProjectTitlePresenterImpl<V extends ProjectTitleMvpView> extends BasePresenter<V> implements ProjectTitleMvpPresenter<V> {


    @Override
    public void initialize(Object... obj) {
        IService service1 = RequestUtils.get().create(IService.class);
        Observable<ProjectTitlePo> list = service1.getProjectTitleList();
        RequestUtils.setRequestListener(new RequestUtils.IRequestListener() {
            @Override
            public void onSuccess(Object o) {

                if(!isViewAttached())
                    return;

                ProjectTitlePo po= (ProjectTitlePo) o;
                Log.d(TAG, "onSuccess: "+po.getData().size());
                getMvpView().getProjectTitleData(po);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
        RequestUtils.toSubscribe(list);
    }
}

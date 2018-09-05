package com.mainconstruction.cn.fragment.presenterImpl;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.commmonlibrary.cn.mvp.BasePresenter;
import com.commmonlibrary.cn.net.RequestUtils;
import com.mainconstruction.cn.fragment.presenter.ProjectContact.ProjectMvpPresenter;
import com.mainconstruction.cn.fragment.presenter.ProjectContact.ProjectMvpView;
import com.mainconstruction.cn.model.ProjectPo;
import com.mainconstruction.cn.net.IService;

import io.reactivex.Observable;

import static com.commmonlibrary.cn.base.BaseFragment.TAG;

/**
 * Created by chawei on 2018/8/26.
 */

public class ProjectPresenterImpl<V extends ProjectMvpView> extends BasePresenter<V> implements ProjectMvpPresenter<V> {


    @Override
    public void initialize(Object... obj) {
        IService service1 = RequestUtils.get().create(IService.class);
        Observable<ProjectPo> list = service1.getProjectList((int)obj[0],(String) obj[1]);
        RequestUtils.setRequestListener(new RequestUtils.IRequestListener() {
            @Override
            public void onSuccess(Object o) {

                if(!isViewAttached())
                    return;

                ProjectPo po= (ProjectPo) o;
                Log.d(TAG, "onSuccess: 项目个数---->"+po.getData().getDatas().size());
                if(po.getData().getDatas().size()==0){
                    ToastUtils.showShort("没数据啦 /(ㄒoㄒ)/~~");
                    return;
                }
                getMvpView().getProjectData(po);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
        RequestUtils.toSubscribe(list);
    }
}

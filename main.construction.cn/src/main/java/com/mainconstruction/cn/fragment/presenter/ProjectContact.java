package com.mainconstruction.cn.fragment.presenter;

import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.commmonlibrary.cn.mvp.MvpView;
import com.mainconstruction.cn.model.ProjectPo;

/**
 * Created by chawei on 2018/8/26.
 */

public class ProjectContact {

    public interface ProjectMvpView extends MvpView{
        void getProjectData(ProjectPo po);
    }

    public interface ProjectMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    }

}

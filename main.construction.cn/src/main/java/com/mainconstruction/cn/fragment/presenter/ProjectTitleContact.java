package com.mainconstruction.cn.fragment.presenter;

import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.commmonlibrary.cn.mvp.MvpView;
import com.mainconstruction.cn.model.ProjectTitlePo;

/**
 * Created by chawei on 2018/8/26.
 */

public class ProjectTitleContact {

    public interface ProjectTitleMvpView extends MvpView{
        void getProjectTitleData(ProjectTitlePo po);
    }

    public interface ProjectTitleMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    }

}

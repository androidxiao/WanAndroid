package com.mainconstruction.cn.net;

import com.mainconstruction.cn.model.ProjectPo;
import com.mainconstruction.cn.model.ProjectTitlePo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chawei on 2018/8/23.
 */

public interface IService {


    @GET("project/tree/json")
    Observable<ProjectTitlePo> getProjectTitleList();

    @GET("project/list/{page}/json")
    Observable<ProjectPo> getProjectList(@Path("page") int page, @Query("cid") String cid);

}

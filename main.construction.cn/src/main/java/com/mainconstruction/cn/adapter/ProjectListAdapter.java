package com.mainconstruction.cn.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.commmonlibrary.cn.adapter.BaseRecyclerAdapter;
import com.mainconstruction.cn.model.ProjectPo;
import com.mainconstruction.cn.viewholder.ProjectListHolder;

/**
 * Created by chawei on 2018/9/2.
 */

public class ProjectListAdapter extends BaseRecyclerAdapter<ProjectPo.DataBean.DatasBean, ProjectListHolder> {

    public ProjectListAdapter(Context context) {
        super(context);
    }

    @Override
    public ProjectListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProjectListHolder.instance(parent);
    }

    @Override
    public void onBindViewHolder(ProjectListHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.bindData(context, datas.get(position));
    }
}

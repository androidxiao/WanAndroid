package com.mainconstruction.cn.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.commmonlibrary.cn.adapter.BaseRecyclerAdapter;
import com.commmonlibrary.cn.imageloader.ImageLoader;
import com.commmonlibrary.cn.imageloader.glide.ImageConfigImpl;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.model.ProjectPo;

/**
 * Created by chawei on 2018/9/2.
 */

public class ProjectListHolder extends BaseRecyclerAdapter.ClickableViewHolder {


    private final ImageView mIvPost;
    private final TextView mTvTitle;
    private final TextView mTvAuthor;
    private final TextView mTvTime;
    private final ImageView mIvCollect;

    public ProjectListHolder(View itemView) {
        super(itemView);

        mIvPost = itemView.findViewById(R.id.iv_post);
        mTvTitle = itemView.findViewById(R.id.tv_title);
        mTvAuthor = itemView.findViewById(R.id.tv_author);
        mTvTime = itemView.findViewById(R.id.tv_time);
        mIvCollect = itemView.findViewById(R.id.iv_collect);


    }

    public static ProjectListHolder instance(ViewGroup container) {
        return new ProjectListHolder(LayoutInflater.from(container.getContext()).inflate(R.layout.appmain_adapter_project_list, container, false));
    }


    public void bindData(Context context, ProjectPo.DataBean.DatasBean data){

        ImageLoader.getInstance().loadImage(context, ImageConfigImpl.builder().url(data.getEnvelopePic()).cacheStrategy(1).isCenterCrop(true).imageView(mIvPost).build());

        mTvTitle.setText(data.getTitle());
        mTvAuthor.setText(data.getAuthor());
        mTvTime.setText(data.getNiceDate());

        boolean collect = data.isCollect();

        if(collect){
            mIvCollect.setBackground(ContextCompat.getDrawable(context,R.mipmap.ic_collect_sel));
        }else{
            mIvCollect.setBackground(ContextCompat.getDrawable(context,R.mipmap.ic_collect));
        }

    }


}

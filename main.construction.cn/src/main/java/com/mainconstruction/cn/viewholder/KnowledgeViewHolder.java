package com.mainconstruction.cn.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.commmonlibrary.cn.adapter.BaseRecyclerAdapter;
import com.mainconstruction.cn.R;
import com.mainconstruction.cn.fragment.KnowledgeFragment;

/**
 * Created by chawei on 2018/8/21.
 */

public class KnowledgeViewHolder extends BaseRecyclerAdapter.ClickableViewHolder{

    private final TextView mTv;

    public KnowledgeViewHolder(View itemView) {
        super(itemView);
        mTv=itemView.findViewById(R.id.textview);
    }


    public static KnowledgeViewHolder instance(ViewGroup container) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.appmain_list_item_type_1, container, false);
        return new KnowledgeViewHolder(view);
    }

    public void bindData(Context context, KnowledgeFragment.KnowledgePo data){
        mTv.setText("第一》》"+data.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "第一个", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

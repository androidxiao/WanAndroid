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

public class KnowledgeViewHolder2 extends BaseRecyclerAdapter.ClickableViewHolder {

    private final TextView mTv;

    public KnowledgeViewHolder2(View itemView) {
        super(itemView);
        mTv = itemView.findViewById(R.id.textview1);
    }


    public static KnowledgeViewHolder2 instance(ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.appmain_list_item_type_2, container, false);
        return new KnowledgeViewHolder2(view);
    }

    public void bindData(Context context, KnowledgeFragment.KnowledgePo data) {
        mTv.setText("第二--》" + data.getTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "第222个", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

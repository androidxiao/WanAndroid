package com.mainconstruction.cn.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.commmonlibrary.cn.adapter.BaseRecyclerAdapter;
import com.mainconstruction.cn.fragment.KnowledgeFragment;
import com.mainconstruction.cn.viewholder.KnowledgeViewHolder;
import com.mainconstruction.cn.viewholder.KnowledgeViewHolder2;

/**
 * Created by chawei on 2018/8/21.
 */

public class KnowledgeAdapter extends BaseRecyclerAdapter<KnowledgeFragment.KnowledgePo,BaseRecyclerAdapter.ClickableViewHolder> {

    public KnowledgeAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerAdapter.ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int type = getItemViewType(viewType);
        if (type == 2) {
            return KnowledgeViewHolder2.instance(parent);
        }else if (type==1){
            return KnowledgeViewHolder.instance(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.ClickableViewHolder holder, int position) {
        if (holder instanceof KnowledgeViewHolder) {
            ((KnowledgeViewHolder) holder).bindData(context,datas.get(position));
        } else if (holder instanceof KnowledgeViewHolder2) {
            ((KnowledgeViewHolder2) holder).bindData(context,datas.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        boolean second = datas.get(position).isSecond();
        if (second) {
            return 2;
        }else{
            return 1;
        }
    }
}

package com.commmonlibrary.cn.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.commmonlibrary.cn.BaseModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by chawei on 2018/8/21.
 */

public abstract class BaseRecyclerAdapter<T extends BaseModel, V extends BaseRecyclerAdapter.ClickableViewHolder> extends RecyclerView.Adapter<V> {
    protected List<T> datas;
    protected final LayoutInflater layoutInflater;
    protected Context context;
    protected BaseRecyclerAdapter.OnItemClickListener onItemClickListener;
    private BaseRecyclerAdapter.OnItemLongClickListener itemLongClickListener;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.datas = Collections.emptyList();
    }

    public long getItemId(int position) {
        return (long)position;
    }

    public int getItemCount() {
        return this.datas == null?0:this.datas.size();
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setDatas(Collection<T> usersCollection) {
        this.validateUsersCollection(usersCollection);
        this.datas = (List)usersCollection;
        this.notifyDataSetChanged();
    }

    public void addDatas(Collection<T> usersCollection) {
        this.validateUsersCollection(usersCollection);
        if(this.datas != null && this.datas.size() != 0) {
            this.datas.addAll(usersCollection);
        } else {
            this.datas = (List)usersCollection;
        }

        this.notifyDataSetChanged();
    }

    protected void validateUsersCollection(Collection<T> usersCollection) {
        if(usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }


    public void onBindViewHolder(final V holder, final int position) {
        holder.getParentView().setOnClickListener(v -> {
            if(BaseRecyclerAdapter.this.onItemClickListener != null) {
                BaseRecyclerAdapter.this.onItemClickListener.onItemClicked(position, holder);
            }

        });
        holder.getParentView().setOnLongClickListener(v -> BaseRecyclerAdapter.this.itemLongClickListener != null?BaseRecyclerAdapter.this.itemLongClickListener.onItemLongClick(position, holder):false);
    }

    public static class ClickableViewHolder extends RecyclerView.ViewHolder {
        private View parentView;

        public ClickableViewHolder(View itemView) {
            super(itemView);
            this.parentView = itemView;
        }

        public View getParentView() {
            return this.parentView;
        }

        public <T extends View> T $(@IdRes int id) {
            return this.parentView.findViewById(id);
        }
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position, BaseRecyclerAdapter.ClickableViewHolder holder);
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, BaseRecyclerAdapter.ClickableViewHolder holder);
    }
}


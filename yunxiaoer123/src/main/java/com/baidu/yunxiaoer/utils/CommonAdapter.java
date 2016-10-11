package com.baidu.yunxiaoer.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xiaoqi on 15/12/17.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> list;
    protected int layoutId;

    public CommonAdapter(Context mContext, List<T> list, int layoutId) {
        this.mContext = mContext;
        this.list = list;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.getViewHolder(mContext, convertView, parent, layoutId, position);
        setData(vh, list.get(position));
        return vh.getConVertView();
    }

    public abstract void setData(ViewHolder vh, T t);
}

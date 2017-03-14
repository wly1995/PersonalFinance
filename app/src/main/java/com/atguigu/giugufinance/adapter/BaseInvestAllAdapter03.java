package com.atguigu.giugufinance.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.giugufinance.holder.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万里洋 on 2017/3/14.
 * 第二次的抽取
 */

public abstract class BaseInvestAllAdapter03<T> extends BaseAdapter {
    public List<T> list = new ArrayList<>();

    public BaseInvestAllAdapter03(List<T> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
        }
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
        BaseHolder baseHolder = null;
        if (convertView == null) {
            //这个方法里面有加载convetView，及其settag，和初始化控件都在baseholder
            baseHolder = getHolder();
        } else {
            baseHolder = (BaseHolder) convertView.getTag();
        }

        T t = list.get(position);
        //传数据到baseholder，供其子类的viewholder使用
        baseHolder.setData(t);
        return baseHolder.getView();
    }

    public abstract BaseHolder getHolder();
}

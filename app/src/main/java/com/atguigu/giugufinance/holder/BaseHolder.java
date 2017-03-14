package com.atguigu.giugufinance.holder;


import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 万里洋 on 2017/3/14.
 */

public abstract class BaseHolder<T> {
    private View rootView;

    public BaseHolder(){
        rootView = initView();
        ButterKnife.bind(this,rootView);
        rootView.setTag(this);
    }
    private T t;

    public  void setData(T t) {
        this.t = (T) t;
        setChildData();
    }

    public T getT(){
        return t;
    }
    public  View getView(){
        return rootView;
    }
    public abstract View initView();
    public abstract void setChildData();
}

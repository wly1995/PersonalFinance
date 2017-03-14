package com.atguigu.giugufinance.adapter;

import com.atguigu.giugufinance.bean.InvestAllBean;
import com.atguigu.giugufinance.holder.BaseHolder;
import com.atguigu.giugufinance.holder.InvestHolder;

import java.util.List;


/**
 * Created by 万里洋 on 2017/3/14.
 * 第二次抽取
 */

public class InvesAllAdapter3 extends BaseInvestAllAdapter03<InvestAllBean.DataBean> {

    public InvesAllAdapter3(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    public BaseHolder getHolder() {
        return new InvestHolder();
    }

}

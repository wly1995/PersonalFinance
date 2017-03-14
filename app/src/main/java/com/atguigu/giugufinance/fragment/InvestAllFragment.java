package com.atguigu.giugufinance.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.adapter.InvesAllAdapter2;
import com.atguigu.giugufinance.bean.InvestAllBean;
import com.atguigu.giugufinance.command.AppNetConfig;

import butterknife.Bind;

/**
 * Created by 万里洋 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.lv_invest_all)
    ListView lvInvestAll;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {
//        Log.e("TAG","json=="+json);
        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);
//        InvestAllAdapter adapter = new InvestAllAdapter(investAllBean.getData());
//        InvesAllAdapter1 adapter = new InvesAllAdapter1(investAllBean.getData());
        InvesAllAdapter2 adapter = new InvesAllAdapter2(investAllBean.getData());
        lvInvestAll.setAdapter(adapter);
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }
}

package com.atguigu.giugufinance.adapter;

import android.view.View;
import android.widget.TextView;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.bean.InvestAllBean;
import com.atguigu.giugufinance.util.UiUtils;

import java.util.List;


/**
 * Created by 万里洋 on 2017/3/14.
 * 第二次抽取
 */

public class InvesAllAdapter2 extends BaseInvestAllAdapter02<InvestAllBean.DataBean> {

    public InvesAllAdapter2(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }

    @Override
    protected void setData(InvestAllBean.DataBean dataBean, View convertView) {
        TextView pname = (TextView) convertView.findViewById(R.id.p_name);
        pname.setText(dataBean.getName());
    }
}

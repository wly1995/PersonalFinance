package com.atguigu.giugufinance.holder;

import android.view.View;
import android.widget.TextView;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.bean.InvestAllBean;
import com.atguigu.giugufinance.ui.MyProgress;
import com.atguigu.giugufinance.util.UiUtils;

import butterknife.Bind;

/**
 * Created by 万里洋 on 2017/3/14.
 */

public class InvestHolder extends BaseHolder<InvestAllBean.DataBean> {
    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    MyProgress pProgresss;

    @Override
    public View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }

    @Override
    public void setChildData() {
        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        pSuodingdays.setText(dataBean.getSuodingDays());
        pYearlv.setText(dataBean.getYearRate());
        pMinnum.setText(dataBean.getMinTouMoney());
        pMinzouzi.setText(dataBean.getMemberNum());
    }
}

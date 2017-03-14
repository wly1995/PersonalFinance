package com.atguigu.giugufinance.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.bean.InvestAllBean;
import com.atguigu.giugufinance.ui.MyProgress;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 万里洋 on 2017/3/14.
 * 第二次抽取
 */

public class InvesAllAdapter1 extends BaseInvestAllAdapter<InvestAllBean.DataBean> {

    public InvesAllAdapter1(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    public View getChildView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_invest_all, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        InvestAllBean.DataBean dataBean = list.get(position);
        viewHolder.pName.setText(dataBean.getName());
        return convertView;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

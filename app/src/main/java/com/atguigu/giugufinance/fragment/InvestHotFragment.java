package com.atguigu.giugufinance.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.giugufinance.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Random;

import butterknife.Bind;

/**
 * Created by 万里洋 on 2017/3/14.
 */

public class InvestHotFragment extends BaseFragment {

    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    protected void initListener() {

    }

    private Random random = new Random();

    @Override
    protected void initData(String json) {
        idFlowlayout.setAdapter(new TagAdapter<String>(datas)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tv, idFlowlayout, false);
//                tv.setBackgroundResource();
                tv.setText(s);
                int red = random.nextInt(200-50)+50;
                int green = random.nextInt(211);
                int blue = random.nextInt(211);
                tv.setTextColor(Color.rgb(red,green,blue));
                return tv;
            }
        });
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                Toast.makeText(getActivity(), datas[position], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_hot;
    }

    @Override
    public String getChildUrl() {
        return null;
    }
}

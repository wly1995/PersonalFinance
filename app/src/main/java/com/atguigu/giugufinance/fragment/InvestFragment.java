package com.atguigu.giugufinance.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.adapter.InvesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 万里洋 on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @Bind(R.id.invest_vp)
    ViewPager investVp;

    @Override
    protected void initListener() {
        investVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvInvestAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(0);
            }
        });
        tvInvestHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(2);
            }
        });
        tvInvestRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(1);
            }
        });
    }

    @Override
    protected void initData(String json) {
        initListener();
        //设置标题
        initTitle();
        //初始化Fragmet
        initFragments();
        //初始化viewPager
        initViewPager();
        //设置默认选中的tab
        initTab();
    }

    private void initTab() {
        selectText(0);
    }

    private void selectText(int i) {
        //把所有的背景色还原成默认值
        hiddenTextViewState();
        switch (i){
            case 0:
                //改变当前的背景色
                tvInvestAll.setBackgroundColor(Color.RED);
                tvInvestAll.setTextColor(Color.BLACK);
                break;
            case 1:
                tvInvestRecommend.setBackgroundColor(Color.RED);
                tvInvestRecommend.setTextColor(Color.BLACK);
                break;
            case 2:
                tvInvestHot.setBackgroundColor(Color.RED);
                tvInvestHot.setTextColor(Color.BLACK);
                break;
        }
    }

    /**
     * 每次默认进来TextView设置为白色
     */
    private void hiddenTextViewState() {
        tvInvestRecommend.setBackgroundColor(Color.WHITE);
        tvInvestHot.setBackgroundColor(Color.WHITE);
        tvInvestAll.setBackgroundColor(Color.WHITE);
    }

    private void initViewPager() {
        investVp.setAdapter(new InvesAdapter(getChildFragmentManager(), fragments));
    }
    private List<BaseFragment> fragments = new ArrayList<>();
    private void initFragments() {
        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());
    }

    private void initTitle() {
        baseSetting.setVisibility(View.GONE);
        baseTitle.setText("投资");
        baseBack.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest;
    }

    @Override
    public String getChildUrl() {
        return null;
    }
}

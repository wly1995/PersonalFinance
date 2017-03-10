package com.atguigu.giugufinance.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.fragment.HomeFragement;
import com.atguigu.giugufinance.fragment.InvestFragment;
import com.atguigu.giugufinance.fragment.MoreFragment;
import com.atguigu.giugufinance.fragment.ProperyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private HomeFragement homeFragement;
    private InvestFragment investFragment;
    private ProperyFragment properyFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        //默认的数据
        switchFragment(R.id.rb_home);
    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        //默认的对应的按钮
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (checkedId){
            case R.id.rb_home:
                if (homeFragement == null){
                    homeFragement = new HomeFragement();
                    ft.add(R.id.fl_main_content,homeFragement);
                }else{

                    ft.show(homeFragement);
                }
            break;

            case R.id.rb_invest:
                if (investFragment == null){
                    investFragment = new InvestFragment();
                    ft.add(R.id.fl_main_content,investFragment);
                }else {

                    ft.show(investFragment);
                }
                break;
            case R.id.rb_property:
                if (properyFragment == null){
                    properyFragment = new ProperyFragment();
                    ft.add(R.id.fl_main_content,properyFragment);
                }else {
                    ft.show(properyFragment);
                }
                    break;
            case R.id.rb_more:
                if (moreFragment == null){
                    moreFragment = new MoreFragment();
                    ft.add(R.id.fl_main_content,moreFragment);
                }else{

                    ft.show(moreFragment);
                }
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏所有的fragment
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        if (homeFragement != null){
            ft.hide(homeFragement);
        }
        if (investFragment != null){
            ft.hide(investFragment);
        }
        if (properyFragment != null){
            ft.hide(properyFragment);
        }
        if (moreFragment != null){
            ft.hide(moreFragment);
        }
    }


    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}

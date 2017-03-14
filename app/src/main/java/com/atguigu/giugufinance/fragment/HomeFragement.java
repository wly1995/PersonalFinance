package com.atguigu.giugufinance.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.bean.HomeBean;
import com.atguigu.giugufinance.command.AppNetConfig;
import com.atguigu.giugufinance.ui.MyProgress;
import com.atguigu.giugufinance.util.ThreadPool;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 万里洋 on 2017/3/10.
 */

public class HomeFragement extends BaseFragment {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @Bind(R.id.home_progress)
    MyProgress progressHome;

    @Override
    protected void initListener() {
        //初始化title
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        tvHomeYearrate.setText(Double.parseDouble(homeBean.getProInfo().getYearRate()) + "%");
        tvHomeProduct.setText(homeBean.getProInfo().getName());
        initBanner(homeBean);//初始化banner
        initProgress(homeBean.getProInfo());
    }


    /**
     * 自定义的progressbar
     *
     * @param proInfo
     */
    private void initProgress(final HomeBean.ProInfoBean proInfo) {
        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                int progress = Integer.parseInt(proInfo.getProgress());
                for (int i = 1; i <= progress; i++) {
                    SystemClock.sleep(20);
                    progressHome.setProgress(i);
                }
            }
        });
    }

    private void initBanner(HomeBean homeBean) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //转化成url集合
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());
        }
        //设置图片集合
        banner.setImages(urls);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_home;
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }
}

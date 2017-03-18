package com.atguigu.giugufinance.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.activity.ColumnActivity;
import com.atguigu.giugufinance.activity.ImageSettingActivity;
import com.atguigu.giugufinance.activity.LineChartActivity;
import com.atguigu.giugufinance.activity.MainActivity;
import com.atguigu.giugufinance.activity.PieActivity;
import com.atguigu.giugufinance.activity.ReChargeActivity;
import com.atguigu.giugufinance.activity.WithDrawActivity;
import com.atguigu.giugufinance.bean.UserInfo;
import com.atguigu.giugufinance.command.AppNetConfig;
import com.atguigu.giugufinance.util.BitmapUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.content.ContentValues.TAG;

/**
 * Created by 万里洋 on 2017/3/10.
 */

public class ProperyFragment extends BaseFragment {


    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected void initListener() {
        llZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PieActivity.class));
            }
        });

        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ColumnActivity.class));
            }
        });

        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LineChartActivity.class));
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReChargeActivity.class));
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WithDrawActivity.class));
            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ImageSettingActivity.class));
            }
        });
    }

    @Override
    protected void initData(String json) {
        MainActivity activity = (MainActivity) getActivity();
        UserInfo user = activity.getUser();
        //设置用户名
        tvMeName.setText(user.getData().getName());

        //设置头像
       /* Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {
                        Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);
                        bitmap.recycle(); //必须把原来的释放掉
                        return circleBitmap;
                    }
                    @Override
                    public String key() {
                        return ""; //不能为空否则会报错
                    }
                })
                .into(ivMeIcon);*/
        Picasso.with(getActivity()).load(AppNetConfig.BASE_URL + "/images/tx.png")
                .transform(new CropCircleTransformation())
                .transform(
                        new ColorFilterTransformation(Color
                                .parseColor("#66FFccff")))
                //第二个参数值越大越模糊
                .transform(new BlurTransformation(getActivity(), 10))
                .into(ivMeIcon);
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_property;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    //从新回来进行回调的方法
    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        //得到是否更新过的布尔值
        Boolean update = activity.isUpdate();
        if (update) {
            //如果改变过，说明设置的头像
            File filesDir = null;
            FileInputStream fis = null;
            try {
                //判断是否挂载了sd卡
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //外部存储路径
                    filesDir = getActivity().getExternalFilesDir("");
                } else {
                    filesDir = getActivity().getFilesDir(); //内部存储路径
                }
                //全路径
                File path = new File(filesDir, "123.png");
                Log.e(TAG, "onResume: "+path.toString() );
                if (path.exists()) {
                    //找到存贮图片的路径开始读取
                    fis = new FileInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    //变成圆形
                    Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);
                    ivMeIcon.setImageBitmap(circleBitmap);
                    //读取完以后布尔值改成false
                    activity.saveImage(false);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

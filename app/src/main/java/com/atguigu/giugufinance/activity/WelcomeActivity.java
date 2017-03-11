package com.atguigu.giugufinance.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.giugufinance.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.progressBar2)
    ProgressBar progressBar2;
    @Bind(R.id.tv_load)
    TextView tvLoad;
    @Bind(R.id.tv_welcome_version)
    TextView tvWelcomeVersion;
    @Bind(R.id.rl_welcome)
    RelativeLayout rlWelcome;
    //    @Bind(R.id.my_progress)
//    ProgressBar myProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        //设置版本号
        setVersion();
        //设置渐变动画
        setAnimation();
    }

    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        rlWelcome.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
                progressBar2.setVisibility(View.GONE);
                tvLoad.setText("加载完成");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setVersion() {
        tvWelcomeVersion.setText(getVersion());
    }

    private String getVersion() {

        PackageManager packageManager = getPackageManager();

        try {
            PackageInfo info = packageManager.getPackageInfo(this.getPackageName(),0);
            String version = info.versionName;
            int versionCode = info.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startMainActivity();
            tvLoad.setText("加载完成");
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

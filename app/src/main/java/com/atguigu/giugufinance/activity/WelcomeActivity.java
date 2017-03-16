package com.atguigu.giugufinance.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.giugufinance.R;
import com.atguigu.giugufinance.util.AppManager;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {

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
    protected void initListener() {

    }

    public void initData() {
        AppManager.getInstance().addActivity(this);
        //设置版本号
        setVersion();
        //设置渐变动画
        setAnimation();
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_welcome;
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
                if (isLogin()){
                    //登录过进入主界面
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }else{
                    //没有登录过进入登录界面
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private boolean isLogin() {
        String name = getUser().getData().getName();
        if (TextUtils.isEmpty(name)){
            return false;
        }
        return true;
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
        AppManager.getInstance().remove(this);//删除这个activity
    }
}

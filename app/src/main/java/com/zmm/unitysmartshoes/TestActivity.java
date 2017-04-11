package com.zmm.unitysmartshoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/10
 * Time:下午2:47
 */

public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();
    public static TestActivity Instance;

    @InjectView(R.id.unity_framelayout)
    FrameLayout mUnityFramelayout;
    public  PermanentUnityPlayer mUnityPlayer2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        mUnityPlayer2 = new PermanentUnityPlayer(this);
        mUnityFramelayout.addView(mUnityPlayer2);

        mUnityPlayer2.UnitySendMessage("Shoe","SelectSence","two");

    }

    public void unityGameStart(){
        Log.d(TAG,"---unity game start 2---");
    }

    @OnClick(R.id.btn_test_quit)
    public void onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnityPlayer2 != null) {
            this.mUnityPlayer2.quit();
            this.mUnityPlayer2 = null;
        }
        Instance = null;
    }
}

package com.zmm.unitysmartshoes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static MainActivity Instance;

    @InjectView(R.id.unity_framelayout)
    FrameLayout mUnityFramelayout;
    @InjectView(R.id.btn_rotate)
    Button mBtnRotate;
    private PermanentUnityPlayer mUnityPlayer;
    private boolean isToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        mUnityPlayer = new PermanentUnityPlayer(this);
        mUnityFramelayout.addView(mUnityPlayer);

        mUnityPlayer.UnitySendMessage("Shoe", "SelectSence", "one");
    }

    @OnClick({R.id.btn_left_rotate, R.id.btn_right_rotate, R.id.btn_up_rotate, R.id.btn_down_rotate,
            R.id.btn_quit, R.id.btn_rotate})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_left_rotate:
                mUnityPlayer.UnitySendMessage("Shoe", "rotateLeft", "4");
                break;
            case R.id.btn_right_rotate:
                mUnityPlayer.UnitySendMessage("Shoe", "rotateRight", "4");
                break;
            case R.id.btn_up_rotate:
                mUnityPlayer.UnitySendMessage("Shoe", "rotateUp", "4");
                break;
            case R.id.btn_down_rotate:
                mUnityPlayer.UnitySendMessage("Shoe", "rotateDown", "4");
                break;
            case R.id.btn_quit:
                enter();
                break;

            case R.id.btn_rotate:
                rotate();
                break;
        }
    }

    private void rotate() {
        mUnityPlayer.UnitySendMessage("Cube", "isStart", "1");
    }

    private void enter() {
//        Intent intent = new Intent(this,TestActivity.class);
//        startActivity(intent);
//        finish();
        if(isToggle){
            mUnityPlayer.UnitySendMessage("Cube", "SelectSence", "one");
            isToggle = false;
        }else {
            mUnityPlayer.UnitySendMessage("Shoe", "SelectSence", "two");
            isToggle = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.resume();
    }

    public void unityGameStart() {
        Log.d(TAG, "---unity game start 1---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnityPlayer != null) {
            this.mUnityPlayer.quit();
            this.mUnityPlayer = null;
        }

        Instance = null;
    }

    public void onConfigurationChanged(Configuration var1) {
        super.onConfigurationChanged(var1);
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.configurationChanged(var1);
    }

    public void onWindowFocusChanged(boolean var1) {
        super.onWindowFocusChanged(var1);
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.windowFocusChanged(var1);
    }

    public boolean dispatchKeyEvent(KeyEvent var1) {
        return var1.getAction() == 2 ? this.mUnityPlayer.injectEvent(var1) : super.dispatchKeyEvent(var1);
    }

    public boolean onKeyUp(int var1, KeyEvent var2) {

        return this.mUnityPlayer.injectEvent(var2);
    }


    public boolean onKeyDown(int var1, KeyEvent var2) {
        return this.mUnityPlayer.injectEvent(var2);
    }

    public boolean onTouchEvent(MotionEvent var1) {
        return this.mUnityPlayer.injectEvent(var1);
    }

    public boolean onGenericMotionEvent(MotionEvent var1) {
        return this.mUnityPlayer.injectEvent(var1);
    }
}

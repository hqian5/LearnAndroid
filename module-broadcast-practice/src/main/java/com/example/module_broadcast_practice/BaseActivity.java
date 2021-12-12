package com.example.module_broadcast_practice;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected Context mContext;

    private LoginCommonReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //向栈内新增活动
        ActivityCollector.addActivity(this);
        Log.d(TAG, getClass().getName());
    }

    /**
     * 注册Receiver
     */
    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("FORCE_OFFLINE");
        filter.addAction("LOGIN_SUCC");
        receiver = new LoginCommonReceiver();
        registerReceiver(receiver, filter);
    }

    /**
     * 注销Receiver
     */
    private void destroyReceiver() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在栈顶时注册
        initReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //不在栈顶注销
        destroyReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除栈内活动
        ActivityCollector.removeActivity(this);
    }
}

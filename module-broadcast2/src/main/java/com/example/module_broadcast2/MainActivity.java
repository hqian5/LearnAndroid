package com.example.module_broadcast2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private OrderedReceiver orderedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOrderedReceiver();
    }

    private void initOrderedReceiver() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("BroadcastTest");
        intentFilter.setPriority(110);//设置优先级
        orderedReceiver = new OrderedReceiver();
        registerReceiver(orderedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(orderedReceiver);
    }
}
package com.example.module_broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private NormalBroadcastReceiver normalBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        initNormalReceive();
        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("BroadcastTest");
                //发送广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });
//        findViewById(R.id.bt_send_2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("NormalBroadcast");//action可不填
//                intent.setComponent(new ComponentName("com.example.module_broadcast2",
//                        "com.example.module_broadcast2.OrderedReceiver"));
//                sendBroadcast(intent);
//            }
//        });
    }

    private void initNormalReceive() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("BroadcastTest");
        intentFilter.setPriority(100);
        normalBroadcastReceiver = new NormalBroadcastReceiver();
        localBroadcastManager.registerReceiver(normalBroadcastReceiver, intentFilter);
    }

    private void initNetworkBroadCastReceiver() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_SERVICE);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(normalBroadcastReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "Network changed" , Toast.LENGTH_SHORT).show();
            if (TextUtils.equals(WifiManager.WIFI_STATE_CHANGED_ACTION, intent.getAction())) {
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_ENABLED);
                if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
                    Toast.makeText(context, "Wifi is enabled", Toast.LENGTH_SHORT).show();
                } else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                    Toast.makeText(context, "Wifi is disabled", Toast.LENGTH_SHORT).show();
                }
            }
            if (TextUtils.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION, intent.getAction())) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (info.getState() == NetworkInfo.State.DISCONNECTED) {
                    Toast.makeText(context, "Wifi is disconnected", Toast.LENGTH_SHORT).show();
                } else if (info.getState() == NetworkInfo.State.CONNECTED) {
                    Toast.makeText(context, "Wifi is connected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
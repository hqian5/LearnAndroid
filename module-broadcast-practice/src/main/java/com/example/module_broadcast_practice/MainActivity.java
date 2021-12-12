package com.example.module_broadcast_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //发送登陆成功广播
        sendLoginBroadcast();
    }


    private void sendLoginBroadcast() {
        Intent intent = new Intent("LOGIN_SUCC");
        sendBroadcast(intent);
    }

    private void initView() {
        tvMain = findViewById(R.id.tv_main);
        if (getIntent() != null) {
            String account = getIntent().getStringExtra("key_account");
            tvMain.setText(account.concat(", hello there"));
        }
        //发送强制下线广播
        findViewById(R.id.bt_force_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }

    /**
     * 打开MainActivity
     *
     * @param context
     * @param account
     */
    public static void startMainAct(Context context, String account) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("key_account", account);
        context.startActivity(intent);
    }
}
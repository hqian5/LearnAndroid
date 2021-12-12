package com.example.module_broadcast_practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class LoginCommonReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("FORCE_OFFLINE")) {
            //接收强制下线广播
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告")
                    .setMessage("长时间未登录，请重新登录")
                    .setCancelable(false)//不可关闭
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭栈内所有活动，并打开登录页
                            ActivityCollector.finishAllActivities();
                            LoginActivity.startLoginAct(context);
                        }
                    })
                    .show();
        } else if (intent.getAction().equals("LOGIN_SUCC")) {
            //接收登陆成功广播
            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
        }
    }
}
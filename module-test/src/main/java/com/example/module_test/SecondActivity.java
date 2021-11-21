package com.example.module_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, toString() + " task id: " + getTaskId());
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndSetResult();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 打开SecondActivity
     *
     * @param context 当前上下文
     * @param data1   参数1
     * @param data2   参数2
     */
    public static void startSecondActivity(Activity context, String data1, int data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivityForResult(intent, data2);
        Toast.makeText(context, "params: param1: " + intent.getStringExtra("param1")
                + "\nParam2: " + intent.getIntExtra("param2", 0), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        finishAndSetResult();
    }

    private void finishAndSetResult() {
        Intent intent1 = new Intent();
        intent1.putExtra("data_return", "second bt1");
        setResult(RESULT_OK, intent1);
        finish();
    }
}
package com.example.module_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, toString() + " task id: " + getTaskId());
        Intent intent = getIntent();
        Toast.makeText(this, "data: " + intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
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
package com.example.demo1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.R;
import com.example.demo1.fragment.LeftMenuFragment;
import com.example.demo1.fragment.RightMenuFragment;
import com.example.demo1.view.MyButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context mContext;
    private Button btTest;
    private String[] strArr = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private int count;

    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x11) {
                count++;
                btTest.setText(strArr[count % 8]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initView();
    }

    private void initView() {
        btTest = findViewById(R.id.bt_test);
        sendMessage();
    }

    private void sendMessage() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x11);
            }
        }, 0, 1000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        Log.i(TAG, "activity中重写的onKeyDown被调用");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "MainActivity重写的onTouchEvent被调用");
        }
        return false;
    }

    private void setToast(String msg) {
        View toastView = getLayoutInflater().inflate(R.layout.item_child, findViewById(R.id.ll_child));
        ImageView imageView = toastView.findViewById(R.id.iv_child);
        TextView textView = toastView.findViewById(R.id.tv_child);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        textView.setText(msg);
        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private ProgressDialog pd1;
    private int progressStart = 0;
    private final static int MAX_COUNT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initView();
    }

    private void initView() {
        Button btnStart = findViewById(R.id.btn_start);
        Button btnEnd = findViewById(R.id.btn_end);
        Button btnDialog1 = findViewById(R.id.btn_dialog3);
        Button btnDialog2 = findViewById(R.id.btn_dialog4);
        Button btnDialogDiy = findViewById(R.id.btn_dialog_diy);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnDialog1.setOnClickListener(this);
        btnDialog2.setOnClickListener(this);
        btnDialogDiy.setOnClickListener(this);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                //这里的话参数依次为 上下文, 标题, 内容, 是否显示进度, 是否可以点击其他区域关闭
                ProgressDialog.show(mContext, "请稍等", "加载中...", false, true);
                break;
            case R.id.btn_end:
                pd1 = new ProgressDialog(mContext);
                pd1.setTitle("请稍后");
                pd1.setMessage("加载中...");
                pd1.setCancelable(true);
                pd1.setIndeterminate(false);
                //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
                pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd1.show();
                break;
            case R.id.btn_dialog3:
                pd1 = new ProgressDialog(mContext);
                pd1.setTitle("下载中");
                pd1.setCancelable(true);
                pd1.setIndeterminate(false);
                pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                new Thread() {
                    public void run() {
                        for (int i = 0; i <= MAX_COUNT; i++) {
                            progressStart = i;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(111);

                        }
                    }
                }.start();
                progressStart = 0;
                break;
            case R.id.btn_dialog4:
                break;
            case R.id.btn_dialog_diy:
                break;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 111) {
                pd1.setProgress(progressStart);
                NumberFormat format = NumberFormat.getPercentInstance();
                format.setMaximumIntegerDigits(3);
                pd1.setMessage("已下载" + format.format(progressStart / 100.0));
                pd1.show();
            }
            if (progressStart == MAX_COUNT) {
                pd1.dismiss();
                setToast("下载完成");
            }
            return true;
        }
    });
}
package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private ProgressDialog progressDialog;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
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
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setTitle("请稍后");
                progressDialog.setMessage("加载中...");
                progressDialog.setCancelable(true);
                progressDialog.setIndeterminate(false);
                //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                break;
            case R.id.btn_dialog3:
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setTitle("下载中");
                progressDialog.setCancelable(true);
                progressDialog.setIndeterminate(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
                Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setToast("已选择" + year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.btn_dialog_diy:
                Calendar calendar1 = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setToast("已选择" + hourOfDay + "点" + minute + "分");
                    }
                }, calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), false);
                timePickerDialog.show();
                break;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 111) {
                progressDialog.setProgress(progressStart);
                NumberFormat format = NumberFormat.getPercentInstance();
                format.setMaximumIntegerDigits(3);
                progressDialog.setMessage("已下载" + format.format(progressStart / 100.0));
                progressDialog.show();
            }
            if (progressStart == MAX_COUNT) {
                progressDialog.dismiss();
                setToast("下载完成");
            }
            return true;
        }
    });
}
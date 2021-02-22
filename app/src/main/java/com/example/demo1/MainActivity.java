package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
    private float touchX;

    public float getTouchX() {
        return touchX;
    }

    public void setTouchX(float touchX) {
        this.touchX = touchX;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        Button btnStart = findViewById(R.id.btn_start);
        Button btnEnd = findViewById(R.id.btn_end);
        Button btnDialog1 = findViewById(R.id.btn_dialog3);
        Button btnDialog2 = findViewById(R.id.btn_dialog4);
        Button btnDialogDiy = findViewById(R.id.btn_dialog_diy);
        //在onTouch中返回true,同时又添加了onClick监听,这时onClick就不会执行了,事件被onTouch消化掉了.来查看一下执行顺序就知道了,
        //onTouchEvent=>performClick=>onClick,所以在onTouch返回true时,同时又添加了onClick监听,正确的处理方法应该是在onTouch中适当的地方执行performClick方法,来触发onClick.
        btnStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.layout_popup_window, null, false);
                    Button btnDelete = view.findViewById(R.id.btn_delete);
                    Button btnUnread = view.findViewById(R.id.btn_unread);
                    Button btnTop = view.findViewById(R.id.btn_top);
                    //构造一个PopupWindow，参数依次是加载的自定义View，宽，高，focusable
                    PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    //设置出现动画
                    popupWindow.setAnimationStyle(R.anim.pop_in);
                    popupWindow.setTouchable(true);
                    popupWindow.showAsDropDown(v, (int) event.getX(), 0);
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setToast("对话框已删除");
                            popupWindow.dismiss();
                        }
                    });
                    btnUnread.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setToast("对话框已标记为未读");
                            popupWindow.dismiss();
                        }
                    });
                    btnTop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setToast("对话框已置顶");
                            popupWindow.dismiss();
                        }
                    });
                }
                //return super.onTouchEvent(event) ：表示该点击消息被捕获后，不消耗掉，继续往下层分发，返回值由父类super.onTouchEvent(event) 决定；
                //return true ：表示该点击消息被处理消耗掉了，不往下层分发
                //return false：表示该点击消息不被处理消耗掉了，继续往下层分发
                return false;
            }
        });
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
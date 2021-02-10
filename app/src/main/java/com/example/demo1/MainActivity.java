package com.example.demo1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private Button btnStart;
    private Button btnEnd;
    private Button btnDialog1;
    private Button btnDialog2;
    private Button btnDialogDiy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initView();
    }

    private void initView() {
        btnStart = findViewById(R.id.btn_start);
        btnEnd = findViewById(R.id.btn_end);
        btnDialog1 = findViewById(R.id.btn_dialog3);
        btnDialog2 = findViewById(R.id.btn_dialog4);
        btnDialogDiy = findViewById(R.id.btn_dialog_diy);
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
        imageView.setImageResource(R.drawable.ic_alipay);
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
                builder = new AlertDialog.Builder(mContext)
                        .setIcon(R.drawable.ic_dva1)
                        .setTitle("普通的dialog")
                        .setMessage("这是一个普通的dialog，请选择以下三个选项")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setToast("你选择了确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setToast("你选择了取消");
                            }
                        })
                        .setNeutralButton("不知道", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setToast("你选择了不知道");
                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_end:
                String[] items = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
                builder = new AlertDialog.Builder(mContext)
                        .setIcon(R.drawable.ic_dva2)
                        .setTitle("请选择日期~")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setToast("你选择了" + items[which]);
                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_dialog3:
                String[] food = {"burger", "fish and chips", "steak", "fried chicken"};
                builder = new AlertDialog.Builder(mContext)
                        .setTitle("Please choose the main dish")
                        .setIcon(R.drawable.ic_dva3)
                        .setSingleChoiceItems(food, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setToast("You have chosen " + food[which]);
                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setToast("Your dish will be delivered soon");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(false);
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_dialog4:
                String[] activities = {"football", "basketball", "card games", "cycling", "hiking", "running", "gym"};
                boolean[] checkedItems = {false, false, false, false, false, false, false};
                builder = new AlertDialog.Builder(mContext)
                        .setTitle("Please choose activities you are going to do during your holiday")
                        .setMultiChoiceItems(activities, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringBuilder stringBuilder = new StringBuilder("You have chosen");
                                for (int i = 0; i < checkedItems.length; i++) {
                                    if (checkedItems[i]) {
                                        stringBuilder.append(" ").append(activities[i]);
                                    }
                                }
                                setToast(stringBuilder.toString());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_dialog_diy:
                View dialogView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_dialog, null, false);
                TextView tvTitle = dialogView.findViewById(R.id.tv_title);
                TextView tvBody = dialogView.findViewById(R.id.tv_body);
                Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
                builder = new AlertDialog.Builder(mContext)
                        .setView(dialogView)
                        .setCancelable(false);
                dialog = builder.create();
                dialog.show();
                tvTitle.setText("是否跳转");
                tvBody.setText("访问百度一下");
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setToast("跳转至浏览器");
                        Uri uri = Uri.parse("https://www.baidu.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }
}
package com.example.uiwidget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Context context;

    private EditText etUi;
    private ImageView ivUi;
    private ProgressBar pbUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_ui).setOnClickListener(this);
        etUi = findViewById(R.id.et_ui);
        ivUi = findViewById(R.id.iv_ui);
        pbUi = findViewById(R.id.pb_ui);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ui:
                showProgressDialog();
                break;
            default:
                break;
        }
    }

    private void setIvRes() {
        ivUi.setImageResource(R.drawable.img_2);
    }

    private void showProgressBar() {
        if (pbUi.getVisibility() == View.VISIBLE) {
            pbUi.setVisibility(View.GONE);
        } else {
            pbUi.setProgress(pbUi.getProgress() + 10);
            pbUi.setVisibility(View.VISIBLE);
        }
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("你好~")
                .setMessage("你很好看~")
                .setCancelable(false)
                .setPositiveButton("十分确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "你好自恋！", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("才不是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "真不领情~", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showProgressDialog() {
        ProgressDialog pb = new ProgressDialog(context);
        pb.setCancelable(true);
        pb.setMessage("加载中...");
        pb.show();
    }
}
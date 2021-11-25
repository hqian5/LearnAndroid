package com.example.ui_custom_views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @Date: 2021/11/26
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_tile, this);
        findViewById(R.id.bt_back).setOnClickListener(this);
        findViewById(R.id.bt_more).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
                break;
            case R.id.bt_more:
                Toast.makeText(getContext(), "more", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}

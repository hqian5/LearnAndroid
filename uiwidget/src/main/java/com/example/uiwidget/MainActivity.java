package com.example.uiwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private EditText etUi;
    private ImageView ivUi;
    private ProgressBar pbUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//                ivUi.setImageResource(R.drawable.img_2);
                if (pbUi.getVisibility() == View.VISIBLE) {
                    pbUi.setVisibility(View.GONE);
                } else {
                    pbUi.setProgress(pbUi.getProgress() + 10);
                    pbUi.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
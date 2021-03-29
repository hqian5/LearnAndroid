package com.example.modulemvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.modulemvp.R;
import com.example.modulemvp.base.BaseActivity;
import com.example.modulemvp.present.MvpPresenter;
import com.example.modulemvp.view.MvpView;

public class MainActivity extends BaseActivity implements View.OnClickListener, MvpView {
    private TextView tvResult;
    private Button btnSuc;
    private Button btnFail;
    private Button btnExp;
    private MvpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity);
        initView();
    }

    private void initView() {
        //初始化presenter
        presenter = new MvpPresenter();
        //绑定presenter和view
        presenter.attachView(this);
        //绑定控件
        tvResult = findViewById(R.id.tv_result);
        btnSuc = findViewById(R.id.btn_success);
        btnFail = findViewById(R.id.btn_fail);
        btnExp = findViewById(R.id.btn_exception);
        //Button事件监听
        btnSuc.setOnClickListener(this);
        btnFail.setOnClickListener(this);
        btnExp.setOnClickListener(this);
    }

    public void getData(View view) {
        presenter.getData("normal");
    }

    public void getFailureData(View view) {
        presenter.getData("failure");
        tvResult.setText("");
    }

    public void getErrorData(View view) {
        presenter.getData("error");
        tvResult.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_success) {
            getData(v);
        } else if (v.getId() == R.id.btn_fail) {
            getFailureData(v);
        } else if (v.getId() == R.id.btn_exception) {
            getErrorData(v);
        }
    }

    @Override
    public void showData(String data) {
        tvResult.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开presenter和view
        presenter.detachView();
    }
}

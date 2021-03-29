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
import com.example.modulemvp.present.MvpPresenter;
import com.example.modulemvp.view.MvpView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MvpView {
    private TextView tvResult;
    private Button btnSuc;
    private Button btnFail;
    private Button btnExp;
    private ProgressDialog progressDialog;
    private MvpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity);
        initView();
    }

    private void initView() {
        //绑定控件
        tvResult = findViewById(R.id.tv_result);
        btnSuc = findViewById(R.id.btn_success);
        btnFail = findViewById(R.id.btn_fail);
        btnExp = findViewById(R.id.btn_exception);
        //初始化进度条
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("加载中");
        //初始化presenter
        presenter = new MvpPresenter(this);
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
    }

    public void getErrorData(View view) {
        presenter.getData("error");
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
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showData(String data) {
        tvResult.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        tvResult.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "网络请求数据出现异常", Toast.LENGTH_SHORT).show();
        tvResult.setText("网络请求数据出现异常");
    }
}

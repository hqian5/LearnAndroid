package com.example.module_broadcast_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText etAccount;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                //模拟登录逻辑
                if (TextUtils.equals("JerryQ", account) && TextUtils.equals("qwqw1212", password)) {
                    MainActivity.startMainAct(mContext, account);
                    finish();
                } else {
                    Toast.makeText(mContext, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 打开LoginActivity
     *
     * @param context
     */
    public static void startLoginAct(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
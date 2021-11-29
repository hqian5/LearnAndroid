package com.example.module_ui_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Context mContext;
    private List<Msg> msgList;

    private RecyclerView rvChat;
    private Spinner spChat;//下拉选择框
    private EditText etChat;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }

    private void initView() {
        rvChat = findViewById(R.id.rv_chat);
        spChat = findViewById(R.id.sp_chat);
        etChat = findViewById(R.id.et_chat);
        findViewById(R.id.bt_chat).setOnClickListener(this);
        initSpinner();
        initRecyclerView();
    }

    /**
     * 初始化选择框
     */
    private void initSpinner() {
        //getResources().getStringArray(R.array.spinner_array)：获取res中定义的选项列表
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinner_array));
        spChat.setAdapter(adapter);
    }

    private void initRecyclerView() {
        msgList = new ArrayList<>();
        adapter = new ChatAdapter(msgList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvChat.setLayoutManager(manager);
        rvChat.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_chat) {
            if (TextUtils.isEmpty(etChat.getText())) {
                Toast.makeText(mContext, "无法发送空白信息", Toast.LENGTH_SHORT).show();
                return;
            }
            String msg = etChat.getText().toString();
            if (TextUtils.equals((CharSequence) spChat.getSelectedItem(), "发送")) {
                msgList.add(new Msg(msg, ChatAdapter.TYPE_SENDER));
            } else if (TextUtils.equals((CharSequence) spChat.getSelectedItem(), "接收")) {
                msgList.add(new Msg(msg, ChatAdapter.TYPE_RECEIVER));
            }
            etChat.setText("");
            adapter.notifyItemInserted(msgList.size() - 1);
            rvChat.scrollToPosition(msgList.size() - 1);
        }
    }
}
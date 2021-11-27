package com.example.module_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView lvUi;

    private final String[] data = {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
            "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"};
    private List<Item> dataItem;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        lvUi = findViewById(R.id.lv_ui);
//        initView();
        initCustomLV();
    }

    private void initView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, data);
        lvUi.setAdapter(adapter);
    }

    private void initCustomLV() {
        initData();
        ItemAdapter adapter = new ItemAdapter(mContext, R.layout.layout_item, dataItem);
        lvUi.setAdapter(adapter);
        lvUi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "Clicked item ".concat(dataItem.get(position).getName()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        dataItem = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            dataItem.add(new Item("name".concat(String.valueOf(i)), R.mipmap.ic_launcher_round));
        }
    }
}
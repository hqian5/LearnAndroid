package com.example.module_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.module_recyclerview.ItemAdapter.TYPE_GRID;
import static com.example.module_recyclerview.ItemAdapter.TYPE_HORIZONTAL;
import static com.example.module_recyclerview.ItemAdapter.TYPE_VERTICAL;
import static com.example.module_recyclerview.ItemAdapter.TYPE_WATER_FALL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Context mContext;
    private List<Item> data;

    private RecyclerView rvUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = getRandomName("Pig".concat(String.valueOf(i)));
            data.add(new Item(name, R.drawable.ic_pig));
        }
        initView();
    }

    private void initView() {
        rvUi = findViewById(R.id.rv_ui);
        ItemAdapter adapter = new ItemAdapter(data, TYPE_VERTICAL);//纵向
//        ItemAdapter adapter = new ItemAdapter(data, TYPE_HORIZONTAL);//横向
//        ItemAdapter adapter = new ItemAdapter(data, TYPE_WATER_FALL);//瀑布流
//        ItemAdapter adapter = new ItemAdapter(data, TYPE_GRID);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager2 = new GridLayoutManager(mContext, 4);
        rvUi.setLayoutManager(manager);
        rvUi.setAdapter(adapter);
    }

    /**
     * 随机生成长度不等的名字
     *
     * @param name
     * @return
     */
    private static String getRandomName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.MultiAutoCompleteTextView;

import com.example.demo1.adpter.ExpandableAdapter;
import com.example.demo1.entity.App;
import com.example.demo1.entity.Group;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private ExpandableListView listView;
    private ExpandableAdapter adapter;
    private ArrayList<Group> gData;
    private ArrayList<ArrayList<App>> cData;
    private ArrayList<App> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.elv_content);
        gData = new ArrayList<>();
        cData = new ArrayList<>();
        gData.add(new Group("娱乐"));
        gData.add(new Group("社交"));
        gData.add(new Group("生活"));

        items = new ArrayList<>();
        items.add(new App("腾讯视频", R.drawable.ic_tx_video));
        items.add(new App("QQ音乐", R.drawable.ic_qq_music));
        items.add(new App("优酷", R.drawable.ic_youku));
        cData.add(items);

        items = new ArrayList<>();
        items.add(new App("微信", R.drawable.ic_wechat));
        items.add(new App("QQ", R.drawable.ic_qq));
        items.add(new App("微博", R.drawable.ic_weibo));
        cData.add(items);

        items = new ArrayList<>();
        items.add(new App("美团", R.drawable.ic_meituan));
        items.add(new App("支付宝", R.drawable.ic_alipay));
        items.add(new App("大众点评", R.drawable.ic_dianping));
        items.add(new App("百度地图", R.drawable.ic_baidu_map));
        cData.add(items);

        adapter = new ExpandableAdapter(getApplicationContext(), gData, cData);
        listView.setAdapter(adapter);
    }
}
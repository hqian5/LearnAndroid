package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.demo1.adpter.GridAdapter;
import com.example.demo1.entity.App;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private GridView apps;
    private ArrayList<App> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btn_start);
        apps = findViewById(R.id.gv_apps);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        initGridView();
    }

    private void initGridView() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add(new App("App" + (mData.size() + 1), R.drawable.ic_launcher_background));
        }
        GridAdapter adapter = new GridAdapter(getApplicationContext(), mData);
        apps.setAdapter(adapter);
    }
}
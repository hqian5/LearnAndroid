package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.adpter.MyAdapter;
import com.example.demo1.entity.App;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button btnStart;
    private Spinner spinnerWeek;
    private Spinner spinnerApp;
    private ArrayList<App> mData;
    private boolean isWeekSelected = false;
    private boolean isAppSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerWeek = findViewById(R.id.sp_week);
        spinnerApp = findViewById(R.id.sp_app);
//        btnStart = findViewById(R.id.btn_start);
//        btnStart.setOnClickListener(this);
        initSpinner();
    }

    private void initSpinner() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add(new App("App" + (mData.size() + 1), R.drawable.ic_launcher_background));
        }
        MyAdapter adapter = new MyAdapter(getApplicationContext(), mData);
        spinnerApp.setAdapter(adapter);

        spinnerWeek.setOnItemSelectedListener(this);
        spinnerApp.setOnItemSelectedListener(this);
        spinnerApp.setSelection(mData.size() - 1);
    }

    @Override
    public void onClick(View v) {
        initSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_week:
                if (isWeekSelected) {
                    Toast.makeText(getApplicationContext(), "你选择了：" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                } else {
                    isWeekSelected = true;
                }
                break;
            case R.id.sp_app:
                if (isAppSelected) {
                    TextView textView = view.findViewById(R.id.tv_name);
                    Toast.makeText(getApplicationContext(), "你喜欢了：" + textView.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    isAppSelected = true;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
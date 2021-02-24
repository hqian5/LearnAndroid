package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.adpter.MyViewPagerAdapter;
import com.example.demo1.entity.App;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ViewPager viewPager;
    private ArrayList<App> apps;
    private MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        TextView textView = findViewById(R.id.tv_main_title);
        textView.setText("简单ViewPager演示");
        viewPager = findViewById(R.id.vp);
        int[] res = {R.drawable.ic_dva1, R.drawable.ic_dva2, R.drawable.ic_dva3, R.drawable.ic_dva4, R.drawable.ic_dva5, R.drawable.ic_dva6};
        apps = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            apps.add(new App("D.va" + i, res[i]));
        }
        adapter = new MyViewPagerAdapter(apps);
        adapter.setOnTouchListener(new MyViewPagerAdapter.OnTouchListener() {
            @Override
            public void onClick(View view, int pos, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    View popupView = LayoutInflater.from(mContext).inflate(R.layout.layout_popup_window, null, false);
                    Button btnDelete = popupView.findViewById(R.id.btn_delete);
                    PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    window.setTouchable(true);
                    window.showAsDropDown(view, (int) event.getX(), (int) event.getY());
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setToast("已删除" + apps.get(pos).getName());
                            apps.remove(pos);
                            window.dismiss();
                            adapter.notifyDataSetChanged();
                            viewPager.setAdapter(adapter);
                        }
                    });
                }
            }
        });
        viewPager.setAdapter(adapter);
    }

    private void setToast(String msg) {
        View toastView = getLayoutInflater().inflate(R.layout.item_child, findViewById(R.id.ll_child));
        ImageView imageView = toastView.findViewById(R.id.iv_child);
        TextView textView = toastView.findViewById(R.id.tv_child);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        textView.setText(msg);
        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}
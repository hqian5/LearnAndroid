package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.adpter.GeneralAdapter;
import com.example.demo1.entity.App;
import com.example.demo1.fragment.ContentFragment;
import com.example.demo1.fragment.LeftMenuFragment;
import com.example.demo1.fragment.RightMenuFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private DrawerLayout drawerLayout;
    private LeftMenuFragment leftMenuFragment;
    private RightMenuFragment rightMenuFragment;
    private FragmentManager manager;
    private Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two_drawers);
        mContext = MainActivity.this;
        initView();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        manager = getSupportFragmentManager();
        drawerLayout = findViewById(R.id.dr_layout);
        leftMenuFragment = (LeftMenuFragment) manager.findFragmentById(R.id.fg_left_menu);
        rightMenuFragment = (RightMenuFragment) manager.findFragmentById(R.id.fg_right_menu);
        leftMenuFragment.setDrawerLayout(drawerLayout);
        rightMenuFragment.setDrawerLayout(drawerLayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        btnOpen = findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.END);
            }
        });
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
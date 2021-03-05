package com.example.demo1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.demo1.R;

public class RightMenuFragment extends Fragment implements View.OnClickListener {

    private DrawerLayout drawerLayout;

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_right_menu, container, false);
        Button button1 = view.findViewById(R.id.btn_menu1);
        Button button2 = view.findViewById(R.id.btn_menu2);
        Button button3 = view.findViewById(R.id.btn_menu3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        return view;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", String.valueOf(v.getId()));
        contentFragment.setArguments(args);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fl_drawer, contentFragment).commit();
        drawerLayout.closeDrawer(Gravity.END);
    }
}

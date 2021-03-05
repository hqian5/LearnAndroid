package com.example.demo1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.demo1.R;
import com.example.demo1.adpter.GeneralAdapter;
import com.example.demo1.entity.App;

import java.util.ArrayList;

public class LeftMenuFragment extends Fragment {

    private DrawerLayout drawerLayout;

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_left_menu, container, false);
        ListView listView = view.findViewById(R.id.lv_drawer_item);
        int[] res = {R.drawable.ic_alipay, R.drawable.ic_baidu_map, R.drawable.ic_dianping, R.drawable.ic_meituan, R.drawable.ic_weibo};
        ArrayList<App> list = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            list.add(new App("name" + i, res[i]));
        }
        GeneralAdapter<App> adapter = new GeneralAdapter<App>(list, R.layout.item_spinner) {
            @Override
            public void bindView(ViewHolder holder, App obj) {
                holder.setImageResource(R.id.iv_icon, obj.getImgId());
                holder.setText(R.id.tv_name, obj.getName());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentFragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("text", list.get(position).getName());
                contentFragment.setArguments(args);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fl_drawer, contentFragment).commit();
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        return view;
    }
}

package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.adpter.GeneralAdapter;
import com.example.demo1.entity.App;
import com.example.demo1.fragment.ContentFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayList<App> list;
    private GeneralAdapter<App> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one_drawer);
        mContext = MainActivity.this;
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.dr_layout);
        listView = findViewById(R.id.lv_drawer_item);
        int[] res = {R.drawable.ic_alipay, R.drawable.ic_baidu_map, R.drawable.ic_dianping, R.drawable.ic_meituan, R.drawable.ic_weibo};
        list = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            list.add(new App("name" + i, res[i]));
        }
        adapter = new GeneralAdapter<App>(list, R.layout.item_spinner) {
            @Override
            public void bindView(ViewHolder holder, App obj) {
                holder.setImageResource(R.id.iv_icon, obj.getImgId());
                holder.setText(R.id.tv_name, obj.getName());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", list.get(position).getName());
        contentFragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fl_drawer, contentFragment).commit();
        drawerLayout.closeDrawer(listView);
    }
}
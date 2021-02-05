package com.example.demo1.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo1.R;
import com.example.demo1.entity.App;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<App> mData;

    public MyAdapter(Context context, ArrayList<App> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, parent, false);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.iv_icon);
            holder.txt = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        App app = mData.get(position);
        holder.img.setImageResource(app.getImgId());
        holder.txt.setText(app.getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView txt;
    }
}

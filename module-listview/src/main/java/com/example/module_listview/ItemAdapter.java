package com.example.module_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @Date: 2021/11/27
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    private int layoutId;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取每条数据的item对象实例
        Item item = getItem(position);

        //缺点：每次执行getView都重复加载布局、获取控件实例，效率十分低下
//        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
//        TextView tv = view.findViewById(R.id.tv_item);
//        ImageView iv = view.findViewById(R.id.iv_item);
//        tv.setText(item.getName());
//        iv.setImageResource(item.getImageId());
//        return view;

        //优化：使用convertView缓存布局，自定义ViewHolder缓存控件实例
        View viewImprove;
        ViewHolder viewHolder;
        if (convertView == null) {
            //convertView为空，创建布局，新建viewHolder实例，将其存储在view中
            viewImprove = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvItem = viewImprove.findViewById(R.id.tv_item);
            viewHolder.ivItem = viewImprove.findViewById(R.id.iv_item);
            viewImprove.setTag(viewHolder);
        } else {
            //不为空，直接获取缓存的convertView和viewHolder
            viewImprove = convertView;
            viewHolder = (ViewHolder) viewImprove.getTag();
        }
        viewHolder.tvItem.setText(item.getName());
        viewHolder.ivItem.setImageResource(item.getImageId());
        return viewImprove;
    }

    static class ViewHolder {
        TextView tvItem;

        ImageView ivItem;
    }
}

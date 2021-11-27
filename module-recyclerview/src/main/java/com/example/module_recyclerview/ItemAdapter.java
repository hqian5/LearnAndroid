package com.example.module_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Date: 2021/11/27
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    public static final int TYPE_VERTICAL = 1;
    public static final int TYPE_HORIZONTAL = 2;
    public static final int TYPE_WATER_FALL = 3;
    public static final int TYPE_GRID = 4;

    private List<Item> mItemList;
    private int mViewType;

    public ItemAdapter(List<Item> mItemList, int mViewType) {
        this.mItemList = mItemList;
        this.mViewType = mViewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = null;
        switch (viewType) {
            case TYPE_VERTICAL:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
                break;
            case TYPE_HORIZONTAL:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_horizon, parent, false);
                break;
            case TYPE_WATER_FALL:
            case TYPE_GRID:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_water_fall, parent, false);
                break;
        }
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(mItemList.get(position).getName());
        holder.iv.setImageResource(mItemList.get(position).getImageId());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked " + mItemList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked TextView " + mItemList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private TextView tv;
        private ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            tv = itemView.findViewById(R.id.tv_item);
            iv = itemView.findViewById(R.id.iv_item);
        }
    }
}

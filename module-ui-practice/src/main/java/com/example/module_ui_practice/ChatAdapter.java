package com.example.module_ui_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Date: 2021/11/29
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public static final int TYPE_SENDER = 0;
    public static final int TYPE_RECEIVER = 1;

    private List<Msg> mData;

    public ChatAdapter(List<Msg> msgList) {
        this.mData = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_msg, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg item = mData.get(position);
        if (item == null) {
            return;
        }
        boolean isLeft = item.getType() == TYPE_RECEIVER;
        boolean isRight = item.getType() == TYPE_SENDER;
        holder.llLeft.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        holder.tvLeft.setText(isLeft ? item.getMsg() : "");
        holder.llRight.setVisibility(isRight ? View.VISIBLE : View.GONE);
        holder.tvRight.setText(isRight ? item.getMsg() : "");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    final static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llLeft;
        TextView tvLeft;
        LinearLayout llRight;
        TextView tvRight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llLeft = itemView.findViewById(R.id.ll_left);
            tvLeft = itemView.findViewById(R.id.tv_left);
            llRight = itemView.findViewById(R.id.ll_right);
            tvRight = itemView.findViewById(R.id.tv_right);
        }
    }
}

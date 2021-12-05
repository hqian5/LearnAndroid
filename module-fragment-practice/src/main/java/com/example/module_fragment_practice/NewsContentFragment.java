package com.example.module_fragment_practice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Date: 2021/12/4
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class NewsContentFragment extends Fragment {

    private LinearLayout llNews;
    private TextView tvTitle;
    private TextView tvContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View rootView) {
        llNews = rootView.findViewById(R.id.ll_news);
        tvTitle = rootView.findViewById(R.id.tv_news_title);
        tvContent = rootView.findViewById(R.id.tv_news_content);
    }

    public void refresh(String title, String content) {
        llNews.setVisibility(View.VISIBLE);
        tvTitle.setText(TextUtils.isEmpty(title) ? "--" : title);
        tvContent.setText(TextUtils.isEmpty(content) ? "--" : content);
    }
}

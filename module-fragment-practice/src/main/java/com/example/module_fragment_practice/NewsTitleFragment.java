package com.example.module_fragment_practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @Date: 2021/12/4
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_title, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Activity加载完成后判断单/双页模式
        //是否能在Activity布局中找到NewsContentFragment：true-双页 false-单页
        isTwoPane = Objects.requireNonNull(getActivity()).findViewById(R.id.fg_news_content) != null;
    }

    private void initView(View rootView) {
        RecyclerView rvTitle = rootView.findViewById(R.id.rv_news_title);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvTitle.setLayoutManager(manager);
        NewsAdapter adapter = new NewsAdapter(initData());
        rvTitle.setAdapter(adapter);
    }

    private List<News> initData() {
        List<News> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new News("Title " + i, initNewsContent("This is news content " + i)));
        }
        return list;
    }

    /**
     * 随机创建新闻内容
     *
     * @param content
     * @return
     */
    private String initNewsContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mData;

        public NewsAdapter(List<News> mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_news_title, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mData.get(position);
            holder.tvTile.setText(news.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isTwoPane) {
                        //双页-直接刷新NewsContentFragment的内容
                        if (getFragmentManager() != null) {
                            NewsContentFragment contentFragment = (NewsContentFragment) getFragmentManager()
                                    .findFragmentById(R.id.fg_news_content);
                            if (contentFragment != null) {
                                contentFragment.refresh(news.getTitle(), news.getContent());
                            }
                        }
                    } else {
                        //单页-打开NewsContentActivity
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTile;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTile = itemView.findViewById(R.id.tv_item_title);
            }
        }
    }
}

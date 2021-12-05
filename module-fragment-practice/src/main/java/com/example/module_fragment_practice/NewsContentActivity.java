package com.example.module_fragment_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        initView();
    }

    private void initView() {
        NewsContentFragment fragment = (NewsContentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fg_news_content);
        Intent data = getIntent();
        if (data != null) {
            String title = data.getStringExtra("news_title");
            String content = data.getStringExtra("news_content");
            if (fragment != null && fragment.isAdded()) {
                fragment.refresh(title, content);
            }
        }
    }

    public static void actionStart(Context context, String title, String content) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", title);
        intent.putExtra("news_content", content);
        context.startActivity(intent);
    }
}
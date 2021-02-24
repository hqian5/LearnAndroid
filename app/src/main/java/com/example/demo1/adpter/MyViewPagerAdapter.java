package com.example.demo1.adpter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demo1.R;
import com.example.demo1.entity.App;

import java.util.ArrayList;

public class MyViewPagerAdapter extends PagerAdapter {

    private ArrayList<App> mList;
    private ImageView imgContent;
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    public ImageView getImgContent() {
        return imgContent;
    }

    public MyViewPagerAdapter() {

    }

    public MyViewPagerAdapter(ArrayList<App> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull android.view.View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_view_pager, container, false);
        container.addView(view);
        bindData(mList.get(position), view);
        imgContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    onTouchListener.onClick(v, position, event);
                }
                return true;
            }
        });
        imageViews.set(position, imgContent);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 绑定数据和视图（View）
     *
     * @param item 数据实体类
     * @param view 视图
     */
    private void bindData(App item, View view) {
        TextView tvTitle = view.findViewById(R.id.tv_title);
        imgContent = view.findViewById(R.id.iv_img);
        tvTitle.setText(item.getName());
        imgContent.setImageResource(item.getImgId());
        imageViews.add(imgContent);
    }

    public interface OnTouchListener {
        void onClick(View view, int pos, MotionEvent event);
    }

    private OnTouchListener onTouchListener;

    public void setOnTouchListener(OnTouchListener listener) {
        this.onTouchListener = listener;
    }
}

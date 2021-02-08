package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.demo1.adpter.ExpandableAdapter;
import com.example.demo1.entity.App;
import com.example.demo1.entity.Group;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private int[] resId = {R.drawable.ic_dva1, R.drawable.ic_dva2, R.drawable.ic_dva3, R.drawable.ic_dva4, R.drawable.ic_dva5, R.drawable.ic_dva6};
    private ViewFlipper viewFlipper;
    private GestureDetector detector;
    private MyGestureListener listener;
    private final static int MIN_MOVE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewFlipper = findViewById(R.id.vf_dva);
        for (int i = 0; i < resId.length; i++) {
            viewFlipper.addView(setImageView(resId[i]));
        }
        listener = new MyGestureListener();
        detector = new GestureDetector(this, listener);
    }

    //重写onTouchEvent触发MyGestureListener里的方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > MIN_MOVE) {
                viewFlipper.setInAnimation(getApplicationContext(), R.anim.right_in);
                viewFlipper.setOutAnimation(getApplicationContext(), R.anim.right_out);
                viewFlipper.showNext();
            } else if (e2.getX() - e1.getX() > MIN_MOVE) {
                viewFlipper.setInAnimation(getApplicationContext(), R.anim.left_in);
                viewFlipper.setOutAnimation(getApplicationContext(), R.anim.left_out);
                viewFlipper.showPrevious();
            }
            return true;
        }
    }

    private ImageView setImageView(int resId) {
        ImageView img = new ImageView(this);
        img.setImageResource(resId);
        return img;
    }
}
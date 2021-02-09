package com.example.demo1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.demo1.adpter.ExpandableAdapter;
import com.example.demo1.entity.App;
import com.example.demo1.entity.Group;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnEnd;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnStart = findViewById(R.id.btn_start);
        btnEnd = findViewById(R.id.btn_end);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
    }


    private void setToast(String msg) {
        View toastView = getLayoutInflater().inflate(R.layout.item_child, findViewById(R.id.ll_child));
        ImageView imageView = toastView.findViewById(R.id.iv_child);
        TextView textView = toastView.findViewById(R.id.tv_child);
        imageView.setImageResource(R.drawable.ic_alipay);
        textView.setText(msg);
        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                String channelId = createNotificationChannel("demo1_channel", "demo1", NotificationManager.IMPORTANCE_MAX);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dva1);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
                builder.setContentTitle("D.va")
                        .setContentText("D.va叫你回家吃饭啦~")
                        .setSubText("D.va在家等你哦")
                        .setTicker("D.va给你发送了一条信息")
                        .setSmallIcon(R.drawable.ic_dianping)
                        .setLargeIcon(bitmap)
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(100, builder.build());
                break;
            case R.id.btn_end:
                if (notificationManager == null) {
                    return;
                }
                notificationManager.cancel(100);
                break;
        }
    }

    private String createNotificationChannel(String channelId, String channelName, int level) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelId, channelName, level);
            manager.createNotificationChannel(channel);
            return channelId;
        } else {
            return null;
        }
    }
}
package com.example.module_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.ACTION_BOOT_COMPLETED;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "The device is booted", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "lalala boot completed");
            Intent start = new Intent(context, MainActivity.class);
            start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(start);
        }
    }
}
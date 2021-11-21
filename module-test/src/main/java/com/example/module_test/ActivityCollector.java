package com.example.module_test;

import android.app.Activity;
import android.os.Process;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity Back Stack Manager
 */
public class ActivityCollector {
    private static final String TAG = "ActivityCollector";

    public static List<Activity> backStack = new ArrayList<>();

    /**
     * 向栈中新增活动
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        backStack.add(activity);
        Log.d(TAG, "Add " + activity.toString() + "\nCurrent stack: " + backStack.toString());
    }

    /**
     * 从栈中移除活动
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        backStack.remove(activity);
        Log.d(TAG, "Remove " + activity.toString() + "\nCurrent stack: " + backStack.toString());
    }

    /**
     * 移除栈中所有活动
     */
    public static void finishAllActivities() {
        for (Activity activity : backStack) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        backStack.clear();
        Log.d(TAG, "Finish All \nCurrent stack: " + backStack.toString());
    }

    /**
     * 退出并关闭App
     */
    public static void killProcess() {
        android.os.Process.killProcess(Process.myPid());
    }
}

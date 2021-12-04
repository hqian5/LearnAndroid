package com.example.module_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FragmentLifeCycle";

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RightFragment rightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_left).setOnClickListener(this);
        findViewById(R.id.bt_toast).setOnClickListener(this);
        rightFragment = new RightFragment();
        replaceFragment(rightFragment);
        Log.d(TAG, "Act-OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Act-OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Act-OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Act-OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Act-OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Act-OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Act-OnRestart");
    }

    private void replaceFragment(Fragment fragment) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
//        transaction.replace(R.id.fl_right, fragment).addToBackStack(null);
//        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_left) {
            replaceFragment(new NewRightFragment());
        } else if (v.getId() == R.id.bt_toast) {
            for (Fragment fragment : manager.getFragments()) {
                if (fragment == null || !fragment.isAdded()) {
                    return;
                }
                if (fragment instanceof LeftFragment) {
                    ((LeftFragment) fragment).toast();
                } else if (fragment instanceof RightFragment) {
                    ((RightFragment) fragment).toast();
                } else if (fragment instanceof NewRightFragment) {
                    ((NewRightFragment) fragment).toast();
                }
            }
        } else if (v.getId() == R.id.bt_right) {
            rightFragment.toastLeft();
        }
    }
}
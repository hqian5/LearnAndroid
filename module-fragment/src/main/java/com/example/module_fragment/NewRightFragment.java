package com.example.module_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Date: 2021/12/1
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class NewRightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_right_fragment, container, false);
    }

    public void toast() {
        Toast.makeText(getActivity(), getClass().getSimpleName() + " toasted", Toast.LENGTH_SHORT).show();
    }
}

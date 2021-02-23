package com.example.demo1;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MTest {

    private final static String TAG = "MTest";

    @Test
    public void test1() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.demo1", appContext.getPackageName());

        Log.i(TAG, "test1 started");
        assertEquals("result: ", 1, 1 + 1);
    }
}

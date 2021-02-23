package com.example.demo1;

import com.example.demo1.Utils.DateUtil;

import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class DateUtilTest {

    private String time = "2021-02-23 11:31:00";

    private long stamp = 1614051060000L;

    @Rule
    public MyRule myRule = new MyRule();

    @Test
    public void dateToStampTest() throws ParseException {
        assertEquals(1614051060000L, DateUtil.dateToStamp(time));
    }

    @Test
    public void stampToDateTest() {
        assertEquals("2021-02-23 11:31:00", DateUtil.stampToDate(1614051060000L));
    }

}

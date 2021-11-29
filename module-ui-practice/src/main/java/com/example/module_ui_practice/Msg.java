package com.example.module_ui_practice;

/**
 * @Date: 2021/11/28
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class Msg {
    private String msg;
    private int type;

    public Msg(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

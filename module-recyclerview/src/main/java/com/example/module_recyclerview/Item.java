package com.example.module_recyclerview;

/**
 * @Date: 2021/11/27
 * @Author: Xiaohei's Laptop
 * @File:
 */
public class Item {
    private String name;
    private int imageId;

    public Item(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

package com.example.modulemvp.base.model;

public class DataModel {
    @SuppressWarnings("rawtypes")
    public static BaseModel request(String token) {
        // 声明一个空的BaseModel
        BaseModel model = null;
        try {
            //利用反射机制获得对应Model对象的引用
            model = (BaseModel) Class.forName(token).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return model;
    }
}

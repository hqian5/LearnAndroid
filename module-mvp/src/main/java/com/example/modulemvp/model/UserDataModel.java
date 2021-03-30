package com.example.modulemvp.model;

import android.os.Handler;

import com.example.modulemvp.base.callback.BaseCallback;
import com.example.modulemvp.base.model.BaseModel;

public class UserDataModel extends BaseModel<String> {
    @Override
    public void execute(BaseCallback<String> callBack) {
        // 利用postDelayed方法模拟网络请求数据的耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mParams[0]) {
                    case "normal":
                        callBack.onSuccess("UserDataModel: 根据参数" + mParams[0] + "的请求网络数据成功");
                        break;
                    case "failure":
                        callBack.onFailure("UserDataModel: 请求失败：参数有误");
                        break;
                    case "error":
                        callBack.onError();
                        break;
                }
                callBack.onComplete();
            }
        }, 2000);
    }
}

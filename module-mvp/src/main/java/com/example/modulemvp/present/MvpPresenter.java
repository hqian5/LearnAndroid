package com.example.modulemvp.present;

import com.example.modulemvp.callback.MvpCallback;
import com.example.modulemvp.model.MvpModel;
import com.example.modulemvp.view.MvpView;

/**
 * Presenter类是具体的逻辑业务处理类，该类为纯Java类，不包含任何Android API，负责请求数据，并对数据请求的反馈进行处理。
 */
public class MvpPresenter {
    // View接口。Presenter类的构造方法中有一个View接口的参数，是为了能够通过View接口通知Activity进行更新界面等操作。
    private MvpView mView;

    public MvpPresenter(MvpView view) {
        this.mView = view;
    }

    /**
     * 获取网络数据
     *
     * @param params 参数
     */
    public void getData(String params) {
        //显示正在加载进度条
        mView.showLoading();
        // 调用Model请求数据
        MvpModel.getNetData(params, new MvpCallback() {
            @Override
            public void onSuccess(String data) {
                //调用view接口显示数据
                mView.showData(data);
            }

            @Override
            public void onFailure(String msg) {
                //调用view接口提示失败信息
                mView.showFailureMessage(msg);
            }

            @Override
            public void onError() {
                //调用view接口提示请求异常
                mView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                // 隐藏正在加载进度条
                mView.hideLoading();
            }
        });
    }

}

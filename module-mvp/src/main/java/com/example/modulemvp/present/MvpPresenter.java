package com.example.modulemvp.present;

import com.example.modulemvp.base.model.DataModel;
import com.example.modulemvp.base.model.Token;
import com.example.modulemvp.base.presenter.BasePresenter;
import com.example.modulemvp.base.callback.BaseCallback;
import com.example.modulemvp.model.MvpModel;
import com.example.modulemvp.view.MvpView;

/**
 * Presenter类是具体的逻辑业务处理类，该类为纯Java类，不包含任何Android API，负责请求数据，并对数据请求的反馈进行处理。
 */
public class MvpPresenter extends BasePresenter<MvpView> {
    // View接口。Presenter类的构造方法中有一个View接口的参数，是为了能够通过View接口通知Activity进行更新界面等操作。
    private MvpView mView;

    public MvpPresenter() {
    }

    /**
     * 获取网络数据
     *
     * @param params 参数
     */
    public void getData(String params) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        DataModel
                // 设置请求标识token
                .request(Token.API_USER_DATA)
                // 添加请求参数，没有则不添加
                .params(params)
                // 注册监听回调
                .execute(new BaseCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        //调用view接口显示数据
                        if (isViewAttached()) {
                            getView().showData(data);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        //调用view接口提示失败信息
                        if (isViewAttached()) {
                            getView().showToast(msg);
                        }
                    }

                    @Override
                    public void onError() {
                        //调用view接口提示请求异常
                        if (isViewAttached()) {
                            getView().showErr();
                        }
                    }

                    @Override
                    public void onComplete() {
                        // 隐藏正在加载进度条
                        if (isViewAttached()) {
                            getView().hideLoading();
                        }
                    }
                });
//        MvpModel.getNetData(params, new BaseCallback<String>() {
//            @Override
//            public void onSuccess(String data) {
//                //调用view接口显示数据
//                if (isViewAttached()) {
//                    getView().showData(data);
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                //调用view接口提示失败信息
//                if (isViewAttached()) {
//                    getView().showToast(msg);
//                }
//            }
//
//            @Override
//            public void onError() {
//                //调用view接口提示请求异常
//                if (isViewAttached()) {
//                    getView().showErr();
//                }
//            }
//
//            @Override
//            public void onComplete() {
//                // 隐藏正在加载进度条
//                if (isViewAttached()) {
//                    getView().hideLoading();
//                }
//            }
//        });
    }

}

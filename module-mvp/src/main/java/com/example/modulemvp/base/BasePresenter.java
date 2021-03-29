package com.example.modulemvp.base;

/**
 * 基类presenter
 *
 * @param <V> 绑定的view类型，都是BaseView的子类
 */
public class BasePresenter<V extends BaseView> {
    /**
     * 绑定的view
     */
    private V mView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.mView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return this.mView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mView;
    }
}

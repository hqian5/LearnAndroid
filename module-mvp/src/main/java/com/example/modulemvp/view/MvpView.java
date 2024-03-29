package com.example.modulemvp.view;

import com.example.modulemvp.base.view.BaseView;

/**
 * View接口是Activity与Presenter层的中间层，它的作用是根据具体业务的需要，为Presenter提供调用Activity中具体UI逻辑操作的方法。
 */
public interface MvpView extends BaseView {
    /**
     * 当数据请求成功后，调用此接口显示数据
     *
     * @param data 数据源
     */
    void showData(String data);
}

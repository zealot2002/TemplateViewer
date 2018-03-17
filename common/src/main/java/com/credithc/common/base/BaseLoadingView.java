package com.credithc.common.base;

import com.zzy.commonlib.base.BaseView;

/**
 * @author zzy
 * @date 2018/2/22
 */
public interface BaseLoadingView extends BaseView {

    void showLoading();

    void closeLoading();

    /*  网络不可用
     *  页面提示：网络异常，带有刷新按钮*/
    void showDisconnect();

    /*  网络可用，但是数据获取失败，
     *  页面提示：网络异常，带有刷新按钮*/
    void showLoadingError();

    /*  重新加载，子类复写  */
    void reload();

    /*  绘制页面，子类复写  */
    void updateUI(Object o);
}


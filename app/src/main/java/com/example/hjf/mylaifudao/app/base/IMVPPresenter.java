package com.example.hjf.mylaifudao.app.base;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 *
 * @author heJianfeng
 * @date 2019/3/22
 */
public interface IMVPPresenter<V extends MvpView> {

    void start();

    void attachView(V mvpView);

    void detachView();

}

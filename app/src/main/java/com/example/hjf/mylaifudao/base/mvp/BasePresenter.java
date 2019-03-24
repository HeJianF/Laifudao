package com.example.hjf.mylaifudao.base.mvp;

import android.support.annotation.CallSuper;

/**
 * 继承此类，可以手动管理MVP的生命周期
 *
 * @author heJianfeng
 * @date 2019/3/24
 */
public class BasePresenter<V extends MvpView> {

    private V mMvpView;

    @CallSuper
    public void attachView(V mvpView) {
        this.mMvpView = mvpView;
    }

    @CallSuper
    public void detachView() {
        mMvpView = null;
    }

    public final boolean isViewAttached() {
        return mMvpView != null;
    }

    public final V getMvpView() {
        return mMvpView;
    }

}

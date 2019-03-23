package com.example.hjf.mylaifudao.base.mvp;

import android.util.Log;

import com.example.hjf.mylaifudao.base.mvp.rxlifecycle.PresenterEvent;
import com.example.hjf.mylaifudao.base.mvp.rxlifecycle.RxLifecyclePresenter;
import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.RxLifecycle;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author heJianfeng
 * @date 2019/3/22
 */
public abstract class MvPPresenter<V extends MvpView> implements IMVPPresenter<V>, LifecycleProvider<Integer> {

    private final BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();

    private V mMvpView;

    @Override
    public void attachView(V mvpView) {
        Log.d("MvPPresenter", "attachView: ");
        mMvpView = mvpView;
        behaviorSubject.onNext(PresenterEvent.ATTACH);
    }

    @Override
    public void detachView() {
        Log.d("MvPPresenter", "detachView: ");
        behaviorSubject.onNext(PresenterEvent.DETACH);
    }

    public V getMvpView() {
        return mMvpView;
    }

    @NonNull
    @Override
    public Observable<Integer> lifecycle() {
        return behaviorSubject.cache();
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecyclePresenter.bindPresenter(behaviorSubject);
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull Integer event) {
        return RxLifecycle.bindUntilEvent(behaviorSubject, event);
    }
}


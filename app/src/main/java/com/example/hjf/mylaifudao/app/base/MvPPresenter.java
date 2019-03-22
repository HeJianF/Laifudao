package com.example.hjf.mylaifudao.app.base;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.OutsideLifecycleException;
import com.trello.rxlifecycle3.RxLifecycle;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author heJianfeng
 * @date 2019/3/22
 */
public abstract class MvPPresenter<V extends MvpView> implements IMVPPresenter<V>, LifecycleProvider<Integer> {

    private static final int ATTACH = 1;
    private static final int DETACH = 0;

    private final BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();

    private V mMvpView;


    @Override
    public void attachView(V mvpView) {
        mvpView = mMvpView;
        behaviorSubject.onNext(ATTACH);
    }

    @Override
    public void detachView() {
        behaviorSubject.onNext(DETACH);
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
        return RxLifecycle.bind(behaviorSubject, integer -> {
            switch (integer) {
                case ATTACH:
                    return DETACH;
                case DETACH:
                    throw new OutsideLifecycleException("Cannot bind to Presenter lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + integer + " not yet implemented");
            }
        });
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull Integer event) {
        return RxLifecycle.bindUntilEvent(behaviorSubject, event);
    }
}


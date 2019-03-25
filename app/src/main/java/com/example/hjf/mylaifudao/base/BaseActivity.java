package com.example.hjf.mylaifudao.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hjf.mylaifudao.base.mvp.BaseLifecyclePresenter;
import com.example.hjf.mylaifudao.base.mvp.MvpView;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author heJianfeng
 * @date 2019/3/24
 */
@SuppressLint("Registered")
public abstract class BaseActivity<P extends BaseLifecyclePresenter> extends RxAppCompatActivity implements MvpView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = provideLayoutId();
        if (id < 0) {
            return;
        }
        setContentView(id);
        ButterKnife.bind(this);

        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initOnCreate(savedInstanceState);
    }

    public abstract int provideLayoutId();

    protected abstract void initOnCreate(Bundle savedInstanceState);

    protected P providePresenter() {
        return null;
    }

    @Override
    public boolean isAlive() {
        return !isFinishing();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}

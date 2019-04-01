package com.example.hjf.mylaifudao.ui.presenter;

import com.example.hjf.mylaifudao.base.mvp.BaseLifecyclePresenter;
import com.example.hjf.mylaifudao.base.mvp.rxlifecycle.PresenterEvent;
import com.example.hjf.mylaifudao.been.LfdInfo;
import com.example.hjf.mylaifudao.model.ModelCommon;
import com.example.hjf.mylaifudao.net.JokeApi;
import com.example.hjf.mylaifudao.net.Retrofit2Create;
import com.example.hjf.mylaifudao.rx.SimpleObserver;
import com.example.hjf.mylaifudao.ui.callback.MainCallBack;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author heJianfeng
 * @date 2019/3/24
 */
public class MainPresenter extends BaseLifecyclePresenter<MainCallBack> {

    @Override
    public void start() {
        loadData();
    }

    public void loadData() {
        final JokeApi jokeApi = Retrofit2Create.LAI_FU_DAO.create(JokeApi.class);

        Observable.zip(jokeApi.getTextData(), jokeApi.getImageData(), (jokeInfos, jokeImageInfos) -> {
            //数据转换
            List<LfdInfo> lfdInfos = new ArrayList<>();
            ModelCommon.lfdResult(lfdInfos, jokeInfos, jokeImageInfos);
            return lfdInfos;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(bindToLifecycle())
                .compose(bindUntilEvent(PresenterEvent.DETACH))
                .subscribe(new SimpleObserver<List<LfdInfo>>() {

                    @Override
                    protected void onHandleSuccess(List<LfdInfo> lfdInfos) {
                        getMvpView().showContent(lfdInfos);
                    }

                    @Override
                    protected void onHandleError(Throwable e, boolean netAvailable) {
                        if (netAvailable) {
                            getMvpView().showErrorPage(e.getMessage());
                        } else {
                            getMvpView().showErrorPage("网络未连接     " + e.getMessage());
                        }
                    }
                });
    }
}

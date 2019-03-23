package com.example.hjf.mylaifudao.ui.callback;

import com.example.hjf.mylaifudao.base.mvp.MvpView;
import com.example.hjf.mylaifudao.been.LfdInfo;

import java.util.List;

/**
 * @author heJianfeng
 * @date 2019/3/24
 */
public interface MainCallBack extends MvpView {

    void loadDataSuccess(List<LfdInfo> data);

    void loadDataError(String message);

}

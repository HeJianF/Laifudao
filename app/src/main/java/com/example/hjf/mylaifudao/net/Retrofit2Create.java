package com.example.hjf.mylaifudao.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author heJianfeng
 * @date 2018/12/13
 */
public enum Retrofit2Create {

    LAI_FU_DAO(1), OTHER(2);

    private final Retrofit retrofit;

    Retrofit2Create(int type) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(4, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl(type == 1 ? "http://api.laifudao.com/open/"
                        : type == 2 ? "http://api.laifudao.com/open/"
                        : "http://api.laifudao.com/open/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}

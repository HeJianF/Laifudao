package com.example.hjf.mylaifudao.net;

import com.example.hjf.mylaifudao.been.ImageInfo;
import com.example.hjf.mylaifudao.been.TextInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author heJianfeng
 * @date 2018/12/13
 */
public interface JokeApi {

    @GET("xiaohua.json")
    Observable<List<TextInfo>> getTextData();

    @GET("tupian.json")
    Observable<List<ImageInfo>> getImageData();
}

package com.example.hjf.mylaifudao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.hjf.mylaifudao.adapter.BaseAdapter;
import com.example.hjf.mylaifudao.adapter.LfdAdapter;
import com.example.hjf.mylaifudao.been.ImageInfo;
import com.example.hjf.mylaifudao.been.LfdInfo;
import com.example.hjf.mylaifudao.been.TextInfo;
import com.example.hjf.mylaifudao.model.ModelCommon;
import com.example.hjf.mylaifudao.net.JokeApi;
import com.example.hjf.mylaifudao.net.Retrofit2Create;
import com.example.hjf.mylaifudao.rx.SimpleObserver;
import com.example.hjf.mylaifudao.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseAdapter.OnAdapterErrorListener {

    private RecyclerView recyclerView;
    private LfdAdapter mLfdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.test_recycler);
        mLfdAdapter = new LfdAdapter(this);
        mLfdAdapter.setErrorListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(mLfdAdapter);

        loadData();
    }

    public void loadData() {
        final JokeApi jokeApi = Retrofit2Create.LAI_FU_DAO.create(JokeApi.class);

        Observable.zip(jokeApi.getTextData(), jokeApi.getImageData(), new BiFunction<List<TextInfo>, List<ImageInfo>, List<LfdInfo>>() {
            @Override
            public List<LfdInfo> apply(List<TextInfo> jokeInfos, List<ImageInfo> jokeImageInfos) {
                //数据转换
                List<LfdInfo> lfdInfos = new ArrayList<>();
                ModelCommon.lfdResult(lfdInfos, jokeInfos, jokeImageInfos);
                return lfdInfos;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<LfdInfo>>() {

                    @Override
                    protected void onHandleSuccess(List<LfdInfo> lfdInfos) {
                        mLfdAdapter.setList(lfdInfos);
                    }

                    @Override
                    protected void onHandleError(Throwable e, boolean netAvailable) {
                        if (netAvailable) {
                            mLfdAdapter.onShowError(e.getMessage());
                        } else {
                            mLfdAdapter.onShowError("网络未连接     " + e.getMessage());
                        }
                    }

                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_title:
                Object o1 = v.getTag();
                if (o1 instanceof TextInfo) {
                    TextInfo textInfo = (TextInfo) o1;
                    ToastUtils.showToast("文本笑话：标题 " + textInfo.getTitle());
                }
                break;
            case R.id.my_content:
                ToastUtils.showToast("文本笑话：内容");
                break;
            case R.id.image_title:
                Object o2 = v.getTag();
                if (o2 instanceof ImageInfo) {
                    ImageInfo imageInfo = (ImageInfo) o2;
                    ToastUtils.showToast("图片笑话：标题" + imageInfo.getTitle());
                }
                break;
            case R.id.image_image:
                ToastUtils.showToast("图片笑话：image");
                break;
           /* case R.id.image_item:
                Object o = v.getTag();
                if (o instanceof ImageInfo) {
                    ImageInfo imageInfo = (ImageInfo) o;
                    Toast.makeText(this, imageInfo.getTitle(), Toast.LENGTH_SHORT).show();
                }
                break;*/
            default:
        }
    }

    @Override
    public void onRetryListener() {
        Toast.makeText(this, "重新加载中...", Toast.LENGTH_SHORT).show();
        loadData();
    }
}

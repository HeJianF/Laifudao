package com.example.hjf.mylaifudao.ui;

import android.os.Bundle;
import android.view.View;

import com.example.hjf.mylaifudao.R;
import com.example.hjf.mylaifudao.adapter.BaseAdapter;
import com.example.hjf.mylaifudao.adapter.LfdAdapter;
import com.example.hjf.mylaifudao.base.BaseActivity;
import com.example.hjf.mylaifudao.been.ImageInfo;
import com.example.hjf.mylaifudao.been.LfdInfo;
import com.example.hjf.mylaifudao.been.TextInfo;
import com.example.hjf.mylaifudao.ui.callback.MainCallBack;
import com.example.hjf.mylaifudao.ui.presenter.MainPresenter;
import com.example.hjf.mylaifudao.utils.ToastUtils;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainCallBack, View.OnClickListener, BaseAdapter.OnAdapterErrorListener {

    @BindView(R.id.test_recycler)
    RecyclerView test_recycler;

    private LfdAdapter mLfdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        mLfdAdapter = new LfdAdapter(this);
        mLfdAdapter.setErrorListener(this);
        test_recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        test_recycler.setAdapter(mLfdAdapter);

        mPresenter.start();
    }

    @Override
    protected MainPresenter providePresenter() {
        return new MainPresenter();
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
        ToastUtils.showToast("重新加载中...");
        mPresenter.loadData();
    }

    @Override
    public void showContent(List<LfdInfo> data) {
        mLfdAdapter.setList(data);
    }

    @Override
    public void showEmptyPage(String emptyInfo) {

    }

    @Override
    public void showErrorPage(String message) {
        mLfdAdapter.onShowError(message);
    }

}

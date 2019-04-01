package com.example.hjf.mylaifudao.adapter;

import androidx.annotation.NonNull;

import android.view.View;
import android.view.ViewGroup;

import com.example.hjf.mylaifudao.been.LfdInfo;
import com.example.hjf.mylaifudao.utils.Constant;
import com.example.hjf.mylaifudao.vh.AdViewHolder;
import com.example.hjf.mylaifudao.vh.BaseViewHolder;
import com.example.hjf.mylaifudao.vh.ImageViewHolder;
import com.example.hjf.mylaifudao.vh.TextViewHolder;

import java.util.List;

/**
 * @author heJianfeng
 * @date 2018/12/13
 */
public class LfdAdapter extends BaseAdapter<LfdInfo> {

    private View.OnClickListener mListener;

    public LfdAdapter(View.OnClickListener listener) {
        super();
        mListener = listener;
        onShowLoading();
    }

    public void setList(List<LfdInfo> lfdInfos) {
        super.setmList(lfdInfos);
    }

    @Override
    protected BaseViewHolder<LfdInfo> onCreateViewHolderInner(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constant.TYPE_TEXT:
                return new TextViewHolder(parent, mListener);
            case Constant.TYPE_IMAGE:
                return new ImageViewHolder(parent, mListener);
            case Constant.TYPE_AD:
                return new AdViewHolder(parent);
            default:
                return null;
        }
    }

    @Override
    protected int getItemViewTypeInner(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position).getType();
        }
        return super.getItemViewTypeInner(position);
    }

    @Override
    protected void onBindViewHolderInner(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder instanceof TextViewHolder) {
            baseViewHolder.bind(mList.get(i));
            return;
        } else if (baseViewHolder instanceof ImageViewHolder) {
            baseViewHolder.bind(mList.get(i));
            return;
        } else if (baseViewHolder instanceof AdViewHolder) {
            return;
        }
        super.onBindViewHolderInner(baseViewHolder, i);
    }

}

package com.example.hjf.mylaifudao.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjf.mylaifudao.R;
import com.example.hjf.mylaifudao.vh.BaseViewHolder;
import com.example.hjf.mylaifudao.vh.ErrorViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseViewHolder> {

    /**
     * 错误
     */
    protected static final int TYPE_ERROR = -1;
    protected boolean isError;
    protected String mErrorString = "";

    List<E> mList = new ArrayList<>();

    public BaseAdapter() {
        isError = false;
    }

    /**
     * 更新数据
     *
     * @param list
     */
    public void setmList(List<E> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        isError = false;
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_ERROR:
                return new ErrorViewHolder(viewGroup, onClickListener);
        }
        return onCreateViewHolderInner(viewGroup, i);
    }

    protected abstract BaseViewHolder<E> onCreateViewHolderInner(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder instanceof ErrorViewHolder) {
            ((ErrorViewHolder) baseViewHolder).bind(mErrorString);
            return;
        }
        onBindViewHolderInner(baseViewHolder, i);
    }

    protected void onBindViewHolderInner(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.bind(getItemInfo(i));
    }

    private E getItemInfo(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (isError) {
            return 1;
        }
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isError) {
            return TYPE_ERROR;
        }
        return getItemViewTypeInner(position);
    }

    protected int getItemViewTypeInner(int position) {
        return 0;
    }

    public void onShowError(String errorStirng) {
        mList.clear();
        isError = true;
        this.mErrorString = errorStirng;
        notifyDataSetChanged();
    }

    private OnAdapterErrorListener errorListener;

    public void setErrorListener(OnAdapterErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fl_error:
                    errorListener.onRetryListener();
                    break;
            }
        }
    };

    public interface OnAdapterErrorListener {

        void onRetryListener();
    }

}

package com.example.hjf.mylaifudao.vh;

import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hjf.mylaifudao.R;
import com.example.hjf.mylaifudao.been.LfdInfo;
import com.example.hjf.mylaifudao.been.TextInfo;

import butterknife.BindView;

/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public class TextViewHolder extends BaseViewHolder<LfdInfo> implements View.OnClickListener {

    @BindView(R.id.my_title)
    TextView title;

    @BindView(R.id.my_content)
    TextView content;

    public TextViewHolder(@NonNull ViewGroup viewGroup, View.OnClickListener listener) {
        super(R.layout.list_item, viewGroup, listener, false);
    }

    @Override
    public void bind(LfdInfo data) {
        Object object = data.getObject();
        if (object instanceof TextInfo) {
            TextInfo info = (TextInfo) object;
            title.setTag(info);
            title.setText(info.getTitle());
            content.setText(Html.fromHtml(info.getContent(),Html.FROM_HTML_MODE_COMPACT));
            title.setOnClickListener(this);
            content.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_title:
                Object o = v.getTag();
                if (o instanceof TextInfo) {
                    TextInfo textInfo = (TextInfo) o;
                    Log.d("TextViewHolder", "onClick: 点击了文本笑话：标题" + textInfo.getTitle());
                }
                break;
            case R.id.my_content:
                Log.d("TextViewHolder", "onClick: 点击了文本笑话：内容");
                break;
        }
        mListener.onClick(v);
    }
}

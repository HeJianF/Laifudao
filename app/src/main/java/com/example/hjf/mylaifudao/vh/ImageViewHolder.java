package com.example.hjf.mylaifudao.vh;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hjf.mylaifudao.R;
import com.example.hjf.mylaifudao.been.ImageInfo;
import com.example.hjf.mylaifudao.been.LfdInfo;

import butterknife.BindView;

/**
 * @author heJianfeng
 * @date 2018/12/13
 */
public class ImageViewHolder extends BaseViewHolder<LfdInfo> implements View.OnClickListener {

    @BindView(R.id.image_title)
    TextView title;

    @BindView(R.id.image_image)
    ImageView image;

    private RequestOptions options;

    public ImageViewHolder(@NonNull ViewGroup viewGroup, View.OnClickListener listener) {
        super(R.layout.image_item, viewGroup, listener, true);
        options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher);
    }

    @Override
    public void bind(LfdInfo data) {
        Object object = data.getObject();
        if (object instanceof ImageInfo) {
            ImageInfo info = (ImageInfo) object;
            title.setTag(info);
            title.setText(info.getTitle());
            Glide.with(itemView.getContext()).
                    load(info.getSourceurl())
                    .apply(options)
                    .into(image);
            title.setOnClickListener(this);
            image.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_title:
                Object o = v.getTag();
                if (o instanceof ImageInfo) {
                    ImageInfo imageInfo = (ImageInfo) o;
                    Log.d("ImageViewHolder", "onClick: 图片笑话：标题" + imageInfo.getTitle());
                }
                break;
            case R.id.image_image:
                Log.d("ImageViewHolder", "onClick: 图片笑话：内容");
                break;
            default:
        }
        mListener.onClick(v);
    }
}

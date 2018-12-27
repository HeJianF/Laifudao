package com.example.hjf.mylaifudao.model;


import com.example.hjf.mylaifudao.been.ImageInfo;
import com.example.hjf.mylaifudao.been.LfdInfo;
import com.example.hjf.mylaifudao.been.TextInfo;
import com.example.hjf.mylaifudao.utils.Constant;

import java.util.List;

/**
 * @author heJianfeng
 * @date 2018/12/18
 * <p>
 * 视图模型通用转换类
 */
public class ModelCommon {

    /**
     * 数据转换
     *
     * @param lfdInfos   最终的数据
     * @param textInfos  纯文本笑话信息
     * @param imageInfos 图片笑话信息
     */
    public static void lfdResult(List<LfdInfo> lfdInfos, List<TextInfo> textInfos, List<ImageInfo> imageInfos) {
        for (int i = 0; i < textInfos.size(); i++) {
            if (i == 5) {
                lfdInfos.add(new LfdInfo(Constant.TYPE_AD, null));
            }
            lfdInfos.add(new LfdInfo(Constant.TYPE_TEXT, textInfos.get(i)));
            lfdInfos.add(new LfdInfo(Constant.TYPE_IMAGE, imageInfos.get(i)));
        }
    }
}

package com.example.hjf.mylaifudao.been;

/**
 * @author heJianfeng
 * @date 2018/12/13
 * <p>
 * 搞笑图片接口
 * 　　http://api.laifudao.com/open/tupian.json
 * 　　title 标题
 * 　　thumburl 大图地址
 * 　　sourceurl 小图地址（宽度为230的图）
 * 　　height 大图高度
 * 　　width 大图宽度
 * 　　url 来源地址
 */
public class ImageInfo {

    private String title;
    private String thumburl;
    private String sourceurl;
    private String height;
    private String width;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

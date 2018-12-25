package com.example.hjf.mylaifudao.been;

/**
 * @author heJianfeng
 * @date 2018/12/13
 * <p>
 * 笑话接口
 * 　　http://api.laifudao.com/open/xiaohua.json
 * 　　title 标题
 * 　　content 内容
 * 　　poster 笑话插图（不是全部笑话都有插图）
 * 　　url 来源地址
 */
public class TextInfo {

    private String title;
    private String content;
    private String poster;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

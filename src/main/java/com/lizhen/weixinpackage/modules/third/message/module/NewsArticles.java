package modules.third.message.module;

import java.io.Serializable;

/**
 * 客服消息的多图文消息
 * Created by lizhen on 2017/8/23.
 */

public class NewsArticles implements Serializable {
    /**
     * 否 图文消息/视频消息/音乐消息/小程序卡片的标题
     */
    private String title;
    /**
     * 否图文消息/视频消息/音乐消息的描述
     */
    private String description;
    /**
     * 否图文消息被点击后跳转的链接
     */
    private String url;
    /**
     * 否图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     */
    private String picurl;


    /**
     * NewsArticles(客服消息的多图文消息) 字符串形式
     *
     * @return NewsArticles(客服消息的多图文消息)字符串
     */
    @Override
    public String toString() {
        return "title:" + title + ",description:" + description + ",url:" + url + ",picurl:" + picurl;
    }

    /**
     * 获取 否图文消息视频消息音乐消息小程序卡片的标题
     *
     * @return title 否图文消息视频消息音乐消息小程序卡片的标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 否图文消息视频消息音乐消息小程序卡片的标题
     *
     * @param title 否图文消息视频消息音乐消息小程序卡片的标题
     * @return 返回 NewsArticles(客服消息的多图文消息)
     */
    public NewsArticles setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 获取 否图文消息视频消息音乐消息的描述
     *
     * @return description 否图文消息视频消息音乐消息的描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置 否图文消息视频消息音乐消息的描述
     *
     * @param description 否图文消息视频消息音乐消息的描述
     * @return 返回 NewsArticles(客服消息的多图文消息)
     */
    public NewsArticles setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 获取 否图文消息被点击后跳转的链接
     *
     * @return url 否图文消息被点击后跳转的链接
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 否图文消息被点击后跳转的链接
     *
     * @param url 否图文消息被点击后跳转的链接
     * @return 返回 NewsArticles(客服消息的多图文消息)
     */
    public NewsArticles setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 获取 否图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080
     *
     * @return picurl 否图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080
     */
    public String getPicurl() {
        return this.picurl;
    }

    /**
     * 设置 否图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080
     *
     * @param picurl 否图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080
     * @return 返回 NewsArticles(客服消息的多图文消息)
     */
    public NewsArticles setPicurl(String picurl) {
        this.picurl = picurl;
        return this;
    }
}

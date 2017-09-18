package modules.weixin.weixinmessage;

import java.io.Serializable;

/**
 * 关注微信回复消息体
 * Created by lizhen on 2017/6/8.
 */
public class Article implements Serializable {

    /**
     * 图文消息名称
     */
    private String title;
    /**
     * 图文消息描述
     */
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，
     */
    private String picUrl;

    /**
     * 点击图文消息跳转链接
     */
    private String url;


    /**
     * Article(关注微信回复消息体) 字符串形式
     *
     * @return Article(关注微信回复消息体)字符串
     */
    @Override
    public String toString() {
        return "title:" + title + ",description:" + description + ",picUrl:" + picUrl + ",url:" + url;
    }

    /**
     * 获取 图文消息名称
     *
     * @return title 图文消息名称
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 图文消息名称
     *
     * @param title 图文消息名称
     * @return 返回 Article(关注微信回复消息体)
     */
    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 获取 图文消息描述
     *
     * @return description 图文消息描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置 图文消息描述
     *
     * @param description 图文消息描述
     * @return 返回 Article(关注微信回复消息体)
     */
    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 获取 图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080，
     *
     * @return picUrl 图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080，
     */
    public String getPicUrl() {
        return this.picUrl;
    }

    /**
     * 设置 图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080，
     *
     * @param picUrl 图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080，
     * @return 返回 Article(关注微信回复消息体)
     */
    public Article setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    /**
     * 获取 点击图文消息跳转链接
     *
     * @return url 点击图文消息跳转链接
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 点击图文消息跳转链接
     *
     * @param url 点击图文消息跳转链接
     * @return 返回 Article(关注微信回复消息体)
     */
    public Article setUrl(String url) {
        this.url = url;
        return this;
    }
}

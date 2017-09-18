package com.lizhen.weixinpackage.modules.weixin.weixinmessage;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 群发消息的图文消息实体类
 * (群发)图文消息，一个图文消息支持1到8条图文
 * Created by lizhen on 2017/5/15.
 */
public class Articles implements Serializable {
    /**
     * 是
     * 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     */
    @JSONField(name = "thumb_media_id")
    private String thumbmediaid;
    /**
     * 否
     * 图文消息的作者
     */
    private String author;
    /**
     * 是
     * 图文消息的标题
     */
    private String title;
    /**
     * 否
     * 在图文消息页面点击“阅读原文”后的页面
     */
    @JSONField(name = "content_source_url")
    private String contentsourceurl;
    /**
     * 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
     */
    private String content;
    /**
     * 否
     * 图文消息的描述
     */
    private String digest;
    /**
     * 否
     * 是否显示封面，1为显示，0为不显示
     */
    @JSONField(name = "show_cover_pic")
    private Integer showcoverpic;


    /**
     * Articles((群发)图文消息，一个图文消息支持1到8条图文) 字符串形式
     *
     * @return Articles((群发)图文消息，一个图文消息支持1到8条图文)字符串
     */
    @Override
    public String toString() {
        return "thumbmediaid:" + thumbmediaid + ",author:" + author + ",title:" + title + ",contentsourceurl:" + contentsourceurl + ",content:" + content
                + ",digest:" + digest + ",showcoverpic:" + showcoverpic;
    }

    /**
     * 获取 是      图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     *
     * @return thumbmediaid 是      图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     */
    public String getThumbmediaid() {
        return this.thumbmediaid;
    }

    /**
     * 设置 是      图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     *
     * @param thumbmediaid 是      图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setThumbmediaid(String thumbmediaid) {
        this.thumbmediaid = thumbmediaid;
        return this;
    }

    /**
     * 获取 否      图文消息的作者
     *
     * @return author 否      图文消息的作者
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * 设置 否      图文消息的作者
     *
     * @param author 否      图文消息的作者
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * 获取 是      图文消息的标题
     *
     * @return title 是      图文消息的标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 是      图文消息的标题
     *
     * @param title 是      图文消息的标题
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 获取 否      在图文消息页面点击“阅读原文”后的页面
     *
     * @return contentsourceurl 否      在图文消息页面点击“阅读原文”后的页面
     */
    public String getContentsourceurl() {
        return this.contentsourceurl;
    }

    /**
     * 设置 否      在图文消息页面点击“阅读原文”后的页面
     *
     * @param contentsourceurl 否      在图文消息页面点击“阅读原文”后的页面
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setContentsourceurl(String contentsourceurl) {
        this.contentsourceurl = contentsourceurl;
        return this;
    }

    /**
     * 获取 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
     *
     * @return content 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
     *
     * @param content 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 获取 否      图文消息的描述
     *
     * @return digest 否      图文消息的描述
     */
    public String getDigest() {
        return this.digest;
    }

    /**
     * 设置 否      图文消息的描述
     *
     * @param digest 否      图文消息的描述
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    /**
     * 获取 否      是否显示封面，1为显示，0为不显示
     *
     * @return showcoverpic 否      是否显示封面，1为显示，0为不显示
     */
    public Integer getShowcoverpic() {
        return this.showcoverpic;
    }

    /**
     * 设置 否      是否显示封面，1为显示，0为不显示
     *
     * @param showcoverpic 否      是否显示封面，1为显示，0为不显示
     * @return 返回 Articles((群发)图文消息，一个图文消息支持1到8条图文)
     */
    public Articles setShowcoverpic(Integer showcoverpic) {
        this.showcoverpic = showcoverpic;
        return this;
    }
}

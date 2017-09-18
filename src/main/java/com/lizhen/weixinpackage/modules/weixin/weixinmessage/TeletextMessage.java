package com.lizhen.weixinpackage.modules.weixin.weixinmessage;

import java.io.Serializable;

/**
 * 群发图文消息
 * Created by lizhen on 2017/5/15.
 */
public class TeletextMessage implements Serializable {
    /**
     * 图文消息集合
     */
    private Articles[] articles;

    /**
     * TeletextMessage(Created by Administrator on 2017515.) 字符串形式
     *
     * @return TeletextMessage(Created by Administrator on 2017515.)字符串
     */
    @Override
    public String toString() {
        return "articles:" + articles;
    }

    /**
     * 获取 图文消息集合
     *
     * @return articles 图文消息集合
     */
    public Articles[] getArticles() {
        return this.articles;
    }

    /**
     * 设置 图文消息集合
     *
     * @param articles 图文消息集合
     * @return 返回 TeletextMessage(Created by Administrator on 2017515.)
     */
    public TeletextMessage setArticles(Articles[] articles) {
        this.articles = articles;
        return this;
    }
}

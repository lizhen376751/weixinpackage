package com.lizhen.weixinpackage.modules.third.message.module;

import java.io.Serializable;

/**
 * 客服的圖文消息
 * Created by lizhen on 2017/8/23.
 */

public class CuatomerNews extends CustomerBaseMessage implements Serializable {
    /**
     * 客服的圖文消息
     */
    private CuatomerArticlessss news;


    /**
     *  CuatomerNews(客服的圖文消息) 字符串形式
     * @return CuatomerNews(客服的圖文消息)字符串
     */
    @Override
    public String toString() {
        return "news:" + news;
    }

    /**
     * 获取 客服的圖文消息
     * @return news 客服的圖文消息
     */
    public CuatomerArticlessss getNews() {
        return this.news;
    }

    /**
     * 设置 客服的圖文消息
     * @param news 客服的圖文消息
     * @return 返回 CuatomerNews(客服的圖文消息)
     */
    public CuatomerNews setNews(CuatomerArticlessss news) {
        this.news = news;
        return this;
    }
}

package com.lizhen.weixinpackage.modules.third.message.module;

import java.io.Serializable;

/**
 * 客服基础接口
 * Created by lizhen on 2017/7/24.
 */
public class CustomerBaseMessage implements Serializable {
    /**
     * 普通用户openid
     */
    private String touser;
    /**
     * 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，
     * 图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard
     */
    private String msgtype;

    /**
     *  CustomerBaseMessage(客服基础接口) 字符串形式
     * @return CustomerBaseMessage(客服基础接口)字符串
     */
    @Override
    public String toString() {
        return "touser:" + touser + ",msgtype:" + msgtype;
    }

    /**
     * 获取 普通用户openid
     * @return touser 普通用户openid
     */
    public String getTouser() {
        return this.touser;
    }

    /**
     * 设置 普通用户openid
     * @param touser 普通用户openid
     * @return 返回 CustomerBaseMessage(客服基础接口)
     */
    public CustomerBaseMessage setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    /**
     * 获取 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，      图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard
     * @return msgtype 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，      图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard
     */
    public String getMsgtype() {
        return this.msgtype;
    }

    /**
     * 设置 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，      图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard
     * @param msgtype 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，      图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard
     * @return 返回 CustomerBaseMessage(客服基础接口)
     */
    public CustomerBaseMessage setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }
}

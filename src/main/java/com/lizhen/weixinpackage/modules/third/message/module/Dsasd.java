package com.lizhen.weixinpackage.modules.third.message.module;

import java.io.Serializable;

/**
 * Created by lizhen on 2017/7/24.
 */

public class Dsasd implements Serializable {
    /**
     * 普通用户openid
     */
    private String touser;
    /**
     * 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard，小程序为miniprogrampage
     */
    private String msgtype;

    /**
     * 3
     */
    private TextContent text;


    /**
     * Dsasd(Created by Administrator on 2017724.) 字符串形式
     *
     * @return Dsasd(Created by Administrator on 2017724.)字符串
     */
    @Override
    public String toString() {
        return "touser:" + touser + ",msgtype:" + msgtype + ",text:" + text;
    }

    /**
     * 获取 1
     *
     * @return touser 1
     */
    public String getTouser() {
        return this.touser;
    }

    /**
     * 设置 1
     *
     * @param touser 1
     * @return 返回 Dsasd(Created by Administrator on 2017724.)
     */
    public Dsasd setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    /**
     * 获取 2
     *
     * @return msgtype 2
     */
    public String getMsgtype() {
        return this.msgtype;
    }

    /**
     * 设置 2
     *
     * @param msgtype 2
     * @return 返回 Dsasd(Created by Administrator on 2017724.)
     */
    public Dsasd setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }

    /**
     * 获取 3
     *
     * @return text 3
     */
    public TextContent getText() {
        return this.text;
    }

    /**
     * 设置 3
     *
     * @param text 3
     * @return 返回 Dsasd(Created by Administrator on 2017724.)
     */
    public Dsasd setText(TextContent text) {
        this.text = text;
        return this;
    }
}

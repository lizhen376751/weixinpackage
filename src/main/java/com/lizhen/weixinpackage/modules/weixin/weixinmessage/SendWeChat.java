package modules.weixin.weixinmessage;

import java.io.Serializable;

/**
 * 根据openid群发消息实体类
 * Created by lizhen on 2017/5/15.
 */
public class SendWeChat implements Serializable {
    /**
     * 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     */
    private String[] touser;
    /**
     *用于群发的图文消息的media_id
     */
    private Mpnews mpnews;
    /**
     * 用于设定即将发送的图文消息
     */
    private String msgtype;

    /**
     * SendWeChat(根据openid群发消息实体类) 字符串形式
     *
     * @return SendWeChat(根据openid群发消息实体类)字符串
     */
    @Override
    public String toString() {
        return "touser:" + touser + ",mpnews:" + mpnews + ",msgtype:" + msgtype;
    }

    /**
     * 获取 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     *
     * @return touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     */
    public String[] getTouser() {
        return this.touser;
    }

    /**
     * 设置 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     *
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @return 返回 SendWeChat(根据openid群发消息实体类)
     */
    public SendWeChat setTouser(String[] touser) {
        this.touser = touser;
        return this;
    }

    /**
     * 获取 用于群发的图文消息的media_id
     *
     * @return mpnews 用于群发的图文消息的media_id
     */
    public Mpnews getMpnews() {
        return this.mpnews;
    }

    /**
     * 设置 用于群发的图文消息的media_id
     *
     * @param mpnews 用于群发的图文消息的media_id
     * @return 返回 SendWeChat(根据openid群发消息实体类)
     */
    public SendWeChat setMpnews(Mpnews mpnews) {
        this.mpnews = mpnews;
        return this;
    }

    /**
     * 获取 用于设定即将发送的图文消息
     *
     * @return msgtype 用于设定即将发送的图文消息
     */
    public String getMsgtype() {
        return this.msgtype;
    }

    /**
     * 设置 用于设定即将发送的图文消息
     *
     * @param msgtype 用于设定即将发送的图文消息
     * @return 返回 SendWeChat(根据openid群发消息实体类)
     */
    public SendWeChat setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }
}

package modules.weixin.weixinmessage;

import java.io.Serializable;

/**
 * 群发消息的入参
 * Created by lizhen on 2017/5/15.
 */
public class ParamSendWeChat implements Serializable {
    /**
     * 微信的appid
     */
    private String appid;
    /**
     * 微信的appsecret
     */
    private String appSecret;
    /**
     * 图片的url
     */
    private String filePath;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息要发给谁
     */
    private String[] touser;

    /**
     * 在图文消息页面点击“阅读原文”后的页面
     */
    private String contentsourceurl;

    /**
     *  ParamSendWeChat(群发消息的入参) 字符串形式
     * @return ParamSendWeChat(群发消息的入参)字符串
     */
    @Override
    public String toString() {
        return "appid:" + appid + ",appSecret:" + appSecret + ",filePath:" + filePath + ",title:" + title + ",content:" + content + ",touser:" + touser
                + ",contentsourceurl:" + contentsourceurl;
    }

    /**
     * 获取 微信的appid
     * @return appid 微信的appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 微信的appid
     * @param appid 微信的appid
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 微信的appsecret
     * @return appSecret 微信的appsecret
     */
    public String getAppSecret() {
        return this.appSecret;
    }

    /**
     * 设置 微信的appsecret
     * @param appSecret 微信的appsecret
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    /**
     * 获取 图片的url
     * @return filePath 图片的url
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * 设置 图片的url
     * @param filePath 图片的url
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * 获取 消息标题
     * @return title 消息标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 消息标题
     * @param title 消息标题
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 获取 消息内容
     * @return content 消息内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 消息内容
     * @param content 消息内容
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 获取 消息要发给谁
     * @return touser 消息要发给谁
     */
    public String[] getTouser() {
        return this.touser;
    }

    /**
     * 设置 消息要发给谁
     * @param touser 消息要发给谁
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setTouser(String[] touser) {
        this.touser = touser;
        return this;
    }

    /**
     * 获取 在图文消息页面点击“阅读原文”后的页面
     * @return contentsourceurl 在图文消息页面点击“阅读原文”后的页面
     */
    public String getContentsourceurl() {
        return this.contentsourceurl;
    }

    /**
     * 设置 在图文消息页面点击“阅读原文”后的页面
     * @param contentsourceurl 在图文消息页面点击“阅读原文”后的页面
     * @return 返回 ParamSendWeChat(群发消息的入参)
     */
    public ParamSendWeChat setContentsourceurl(String contentsourceurl) {
        this.contentsourceurl = contentsourceurl;
        return this;
    }
}

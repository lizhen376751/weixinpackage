package modules.weixin.http;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/13.
 */
public class WeixinBaseParamter implements Serializable {

    /**
     * appid
     */
    private String appid;

    /**
     * secret
     */
    private String secret;
    /**
     * 开发者token
     */
    private String  token;

    /**
     *  WeixinBaseParamter(Created by Administrator on 2017413.) 字符串形式
     * @return WeixinBaseParamter(Created by Administrator on 2017413.)字符串
     */
    @Override
    public String toString() {
        return "appid:" + appid + ",secret:" + secret + ",token:" + token;
    }

    /**
     * 获取 appid
     *
     * @return appid appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 appid
     *
     * @param appid appid
     * @return 返回 WeixinBaseParamter(Created by Administrator on 2017413.)
     */
    public WeixinBaseParamter setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 secret
     *
     * @return secret secret
     */
    public String getSecret() {
        return this.secret;
    }

    /**
     * 设置 secret
     *
     * @param secret secret
     * @return 返回 WeixinBaseParamter(Created by Administrator on 2017413.)
     */
    public WeixinBaseParamter setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    /**
     * 获取 开发者token
     *
     * @return token 开发者token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 设置 开发者token
     *
     * @param token 开发者token
     * @return 返回 WeixinBaseParamter(Created by Administrator on 2017413.)
     */
    public WeixinBaseParamter setToken(String token) {
        this.token = token;
        return this;
    }
}

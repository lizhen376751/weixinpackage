package modules.weixin.http.module.parammodule;

import java.io.Serializable;
import java.util.Date;

/**
 * 开发者的token的实体类
 * Created by lizhen on 2017/4/14.
 */
public class AccessToken implements Serializable {
    /**
     * 主键id
     */
    private int id;

    /**
     * appid
     */
    private String appid;
    /**
     * appsecret
     */
    private String appsecret;

    /**
     * token
     */
    private String token;
    /**
     * 调用微信JS接口的临时票据
     */
    private String ticket;
    /**
     * 有效时间
     */
    private int expiresIn;
    /**
     * token以及ticket创建时间
     */
    private long createTime = new Date().getTime() / 1000;


    /**
     *  AccessToken(开发者的token的实体类) 字符串形式
     * @return AccessToken(开发者的token的实体类)字符串
     */
    @Override
    public String toString() {
        return "id:" + id + ",appid:" + appid + ",appsecret:" + appsecret + ",token:" + token + ",ticket:" + ticket + ",expiresIn:" + expiresIn
                + ",createTime:" + createTime;
    }

    /**
     * 获取 主键id
     * @return id 主键id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 主键id
     * @param id 主键id
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 appid
     * @return appid appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 appid
     * @param appid appid
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 appsecret
     * @return appsecret appsecret
     */
    public String getAppsecret() {
        return this.appsecret;
    }

    /**
     * 设置 appsecret
     * @param appsecret appsecret
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setAppsecret(String appsecret) {
        this.appsecret = appsecret;
        return this;
    }

    /**
     * 获取 token
     * @return token token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 设置 token
     * @param token token
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * 获取 调用微信JS接口的临时票据
     * @return ticket 调用微信JS接口的临时票据
     */
    public String getTicket() {
        return this.ticket;
    }

    /**
     * 设置 调用微信JS接口的临时票据
     * @param ticket 调用微信JS接口的临时票据
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    /**
     * 获取 有效时间
     * @return expiresIn 有效时间
     */
    public int getExpiresIn() {
        return this.expiresIn;
    }

    /**
     * 设置 有效时间
     * @param expiresIn 有效时间
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    /**
     * 获取 token以及ticket创建时间
     * @return createTime token以及ticket创建时间
     */
    public long getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 token以及ticket创建时间
     * @param createTime token以及ticket创建时间
     * @return 返回 AccessToken(开发者的token的实体类)
     */
    public AccessToken setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }
}

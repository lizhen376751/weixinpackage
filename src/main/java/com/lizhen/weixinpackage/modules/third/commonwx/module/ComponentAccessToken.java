package com.lizhen.weixinpackage.modules.third.commonwx.module;

import java.io.Serializable;

/**
 * 微信第三方平台的token
 * Created by lizhen on 2017/7/20.
 */

public class ComponentAccessToken implements Serializable {
    /**
     * 第三方平台appid
     */
    private String appid;
    /**
     * 第三方平台access_token
     */
    private String componentAccessToken;
    /**
     * 有效期
     */
    private String expiresIn;

    /**
     * 保存时间
     */
    private Long tokenTime;


    /**
     *  ComponentAccessToken(微信第三方平台的token) 字符串形式
     * @return ComponentAccessToken(微信第三方平台的token)字符串
     */
    @Override
    public String toString() {
        return "appid:" + appid + ",componentAccessToken:" + componentAccessToken + ",expiresIn:" + expiresIn + ",tokenTime:" + tokenTime;
    }

    /**
     * 获取 第三方平台appid
     * @return appid 第三方平台appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 第三方平台appid
     * @param appid 第三方平台appid
     * @return 返回 ComponentAccessToken(微信第三方平台的token)
     */
    public ComponentAccessToken setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 第三方平台access_token
     * @return componentAccessToken 第三方平台access_token
     */
    public String getComponentAccessToken() {
        return this.componentAccessToken;
    }

    /**
     * 设置 第三方平台access_token
     * @param componentAccessToken 第三方平台access_token
     * @return 返回 ComponentAccessToken(微信第三方平台的token)
     */
    public ComponentAccessToken setComponentAccessToken(String componentAccessToken) {
        this.componentAccessToken = componentAccessToken;
        return this;
    }

    /**
     * 获取 有效期
     * @return expiresIn 有效期
     */
    public String getExpiresIn() {
        return this.expiresIn;
    }

    /**
     * 设置 有效期
     * @param expiresIn 有效期
     * @return 返回 ComponentAccessToken(微信第三方平台的token)
     */
    public ComponentAccessToken setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    /**
     * 获取 保存时间
     * @return tokenTime 保存时间
     */
    public Long getTokenTime() {
        return this.tokenTime;
    }

    /**
     * 设置 保存时间
     * @param tokenTime 保存时间
     * @return 返回 ComponentAccessToken(微信第三方平台的token)
     */
    public ComponentAccessToken setTokenTime(Long tokenTime) {
        this.tokenTime = tokenTime;
        return this;
    }
}

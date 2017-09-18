package com.lizhen.weixinpackage.modules.third.commonwx.module;

import java.io.Serializable;

/**
 * 获取预授权码
 * Created by lizhen on 2017/7/20.
 */

public class PreAuthCode implements Serializable {
    /**
     * 第三方平台的appid
     */
    private String appid;
    /**
     * 预授权码
     */
    private String preAuthCode;
    /**
     * 有效期，为20分钟
     */
    private String expiresIn;

    /**
     * 保存时间
     */
    private Long preAuthCodeTime;


    /**
     *  PreAuthCode(获取预授权码) 字符串形式
     * @return PreAuthCode(获取预授权码)字符串
     */
    @Override
    public String toString() {
        return "appid:" + appid + ",preAuthCode:" + preAuthCode + ",expiresIn:" + expiresIn + ",preAuthCodeTime:" + preAuthCodeTime;
    }

    /**
     * 获取 第三方平台的appid
     * @return appid 第三方平台的appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 第三方平台的appid
     * @param appid 第三方平台的appid
     * @return 返回 PreAuthCode(获取预授权码)
     */
    public PreAuthCode setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 预授权码
     * @return preAuthCode 预授权码
     */
    public String getPreAuthCode() {
        return this.preAuthCode;
    }

    /**
     * 设置 预授权码
     * @param preAuthCode 预授权码
     * @return 返回 PreAuthCode(获取预授权码)
     */
    public PreAuthCode setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
        return this;
    }

    /**
     * 获取 有效期，为20分钟
     * @return expiresIn 有效期，为20分钟
     */
    public String getExpiresIn() {
        return this.expiresIn;
    }

    /**
     * 设置 有效期，为20分钟
     * @param expiresIn 有效期，为20分钟
     * @return 返回 PreAuthCode(获取预授权码)
     */
    public PreAuthCode setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    /**
     * 获取 保存时间
     * @return preAuthCodeTime 保存时间
     */
    public Long getPreAuthCodeTime() {
        return this.preAuthCodeTime;
    }

    /**
     * 设置 保存时间
     * @param preAuthCodeTime 保存时间
     * @return 返回 PreAuthCode(获取预授权码)
     */
    public PreAuthCode setPreAuthCodeTime(Long preAuthCodeTime) {
        this.preAuthCodeTime = preAuthCodeTime;
        return this;
    }
}

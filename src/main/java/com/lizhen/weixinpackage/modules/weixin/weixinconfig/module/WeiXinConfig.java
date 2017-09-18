package com.lizhen.weixinpackage.modules.weixin.weixinconfig.module;

import java.io.Serializable;

/**
 * 微信公众号的相关配置数据
 * Created by lizhen on 2017/4/20.
 */
public class WeiXinConfig implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 联盟code或者店铺code
     */
    private String code;
    /**
     * 微信公众号的appid
     */
    private String appid;
    /**
     * 微信公众号的appserect
     */
    private String appserect;

    /**
     *  WeiXinConfig(微信公众号的相关配置数据) 字符串形式
     * @return WeiXinConfig(微信公众号的相关配置数据)字符串
     */
    @Override
    public String toString() {
        return "id:" + id + ",code:" + code + ",appid:" + appid + ",appserect:" + appserect;
    }

    /**
     * 获取 主键id
     *
     * @return id 主键id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 主键id
     *
     * @param id 主键id
     * @return 返回 WeiXinConfig(微信公众号的相关配置数据)
     */
    public WeiXinConfig setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 联盟code或者店铺code
     *
     * @return code 联盟code或者店铺code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置 联盟code或者店铺code
     *
     * @param code 联盟code或者店铺code
     * @return 返回 WeiXinConfig(微信公众号的相关配置数据)
     */
    public WeiXinConfig setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 获取 微信公众号的appid
     *
     * @return appid 微信公众号的appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 微信公众号的appid
     *
     * @param appid 微信公众号的appid
     * @return 返回 WeiXinConfig(微信公众号的相关配置数据)
     */
    public WeiXinConfig setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 微信公众号的appserect
     *
     * @return appserect 微信公众号的appserect
     */
    public String getAppserect() {
        return this.appserect;
    }

    /**
     * 设置 微信公众号的appserect
     *
     * @param appserect 微信公众号的appserect
     * @return 返回 WeiXinConfig(微信公众号的相关配置数据)
     */
    public WeiXinConfig setAppserect(String appserect) {
        this.appserect = appserect;
        return this;
    }
}

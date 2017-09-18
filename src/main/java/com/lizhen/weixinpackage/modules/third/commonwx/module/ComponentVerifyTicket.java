package com.lizhen.weixinpackage.modules.third.commonwx.module;

import java.io.Serializable;

/**
 * 每十分钟推送过来的协议
 * 推送component_verify_ticket协议
 * Created by lizhen on 2017/7/18.
 */

public class ComponentVerifyTicket implements Serializable {

    /**
     * 第三方平台appid
     */
    private String appId;
    /**
     * 第三方的appsecret
     */
    private String appsecret;
    /**
     * 时间戳
     */
    private String createTime;
    /**
     * 暂时不知道什么意思
     */
    private String infoType;
    /**
     * Ticket内容
     */
    private String componentVerifyTicket;
    /**
     * 保存时间
     */
    private Long ticketTime;


    /**
     *  ComponentVerifyTicket(每十分钟推送过来的协议) 字符串形式
     * @return ComponentVerifyTicket(每十分钟推送过来的协议)字符串
     */
    @Override
    public String toString() {
        return "appId:" + appId + ",appsecret:" + appsecret + ",createTime:" + createTime + ",infoType:" + infoType + ",componentVerifyTicket:" + componentVerifyTicket
                + ",ticketTime:" + ticketTime;
    }

    /**
     * 获取 第三方平台appid
     * @return appId 第三方平台appid
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 设置 第三方平台appid
     * @param appId 第三方平台appid
     * @return 返回 ComponentVerifyTicket(每十分钟推送过来的协议)
     */
    public ComponentVerifyTicket setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * 获取 第三方的appsecret
     * @return appsecret 第三方的appsecret
     */
    public String getAppsecret() {
        return this.appsecret;
    }

    /**
     * 设置 第三方的appsecret
     * @param appsecret 第三方的appsecret
     * @return 返回 ComponentVerifyTicket(每十分钟推送过来的协议)
     */
    public ComponentVerifyTicket setAppsecret(String appsecret) {
        this.appsecret = appsecret;
        return this;
    }

    /**
     * 获取 时间戳
     * @return createTime 时间戳
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 时间戳
     * @param createTime 时间戳
     * @return 返回 ComponentVerifyTicket(每十分钟推送过来的协议)
     */
    public ComponentVerifyTicket setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 获取 暂时不知道什么意思
     * @return infoType 暂时不知道什么意思
     */
    public String getInfoType() {
        return this.infoType;
    }

    /**
     * 设置 暂时不知道什么意思
     * @param infoType 暂时不知道什么意思
     * @return 返回 ComponentVerifyTicket(每十分钟推送过来的协议)
     */
    public ComponentVerifyTicket setInfoType(String infoType) {
        this.infoType = infoType;
        return this;
    }

    /**
     * 获取 Ticket内容
     * @return componentVerifyTicket Ticket内容
     */
    public String getComponentVerifyTicket() {
        return this.componentVerifyTicket;
    }

    /**
     * 设置 Ticket内容
     * @param componentVerifyTicket Ticket内容
     * @return 返回 ComponentVerifyTicket(每十分钟推送过来的协议)
     */
    public ComponentVerifyTicket setComponentVerifyTicket(String componentVerifyTicket) {
        this.componentVerifyTicket = componentVerifyTicket;
        return this;
    }

    /**
     * 获取 保存时间
     * @return ticketTime 保存时间
     */
    public Long getTicketTime() {
        return this.ticketTime;
    }

    /**
     * 设置 保存时间
     * @param ticketTime 保存时间
     * @return 返回 ComponentVerifyTicket(每十分钟推送过来的协议)
     */
    public ComponentVerifyTicket setTicketTime(Long ticketTime) {
        this.ticketTime = ticketTime;
        return this;
    }
}

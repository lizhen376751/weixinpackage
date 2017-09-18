package com.lizhen.weixinpackage.modules.third.commonwx.module;

import java.io.Serializable;

/**
 * 加密或者解密所需要的参数
 * Created by lizhen on 2017/8/5.
 */

public class AESParams implements Serializable {
    /**
     * 第三方公众号消息校验Token
     */
    private String token;
    /**
     * 公众号消息加解密Key
     */
    private String encodingAesKey;
    /**
     * 第三方appid
     */
    private String appId;
    /**
     * 签名
     */
    private String msgSignature;
    /**
     * 日期戳
     */
    private String timestamp;
    /**
     * 随机字符串
     */
    private String nonce;
    /**
     * xml
     */
    private String xml;


    /**
     * AESParams(加密或者解密所需要的参数) 字符串形式
     *
     * @return AESParams(加密或者解密所需要的参数)字符串
     */
    @Override
    public String toString() {
        return "token:" + token + ",encodingAesKey:" + encodingAesKey + ",appId:" + appId + ",msgSignature:" + msgSignature + ",timestamp:" + timestamp
                + ",nonce:" + nonce + ",xml:" + xml;
    }

    /**
     * 获取 第三方公众号消息校验Token
     *
     * @return token 第三方公众号消息校验Token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 设置 第三方公众号消息校验Token
     *
     * @param token 第三方公众号消息校验Token
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * 获取 公众号消息加解密Key
     *
     * @return encodingAesKey 公众号消息加解密Key
     */
    public String getEncodingAesKey() {
        return this.encodingAesKey;
    }

    /**
     * 设置 公众号消息加解密Key
     *
     * @param encodingAesKey 公众号消息加解密Key
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
        return this;
    }

    /**
     * 获取 第三方appid
     *
     * @return appId 第三方appid
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 设置 第三方appid
     *
     * @param appId 第三方appid
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * 获取 签名
     *
     * @return msgSignature 签名
     */
    public String getMsgSignature() {
        return this.msgSignature;
    }

    /**
     * 设置 签名
     *
     * @param msgSignature 签名
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
        return this;
    }

    /**
     * 获取 日期戳
     *
     * @return timestamp 日期戳
     */
    public String getTimestamp() {
        return this.timestamp;
    }

    /**
     * 设置 日期戳
     *
     * @param timestamp 日期戳
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 获取 随机字符串
     *
     * @return nonce 随机字符串
     */
    public String getNonce() {
        return this.nonce;
    }

    /**
     * 设置 随机字符串
     *
     * @param nonce 随机字符串
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    /**
     * 获取 xml
     *
     * @return xml xml
     */
    public String getXml() {
        return this.xml;
    }

    /**
     * 设置 xml
     *
     * @param xml xml
     * @return 返回 AESParams(加密或者解密所需要的参数)
     */
    public AESParams setXml(String xml) {
        this.xml = xml;
        return this;
    }
}

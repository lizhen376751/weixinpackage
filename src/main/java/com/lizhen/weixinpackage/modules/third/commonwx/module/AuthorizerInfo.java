package modules.third.commonwx.module;


import modules.third.authorizationinfo.module.AuthorizationInfo;

import java.io.Serializable;

/**
 * 获取授权方的帐号基本信息
 * Created by lizhen on 2017/7/21.
 */

public class AuthorizerInfo implements Serializable {
    /**
     * 授权方昵称
     */
    private String nickName;
    /**
     * 授权方头像
     */
    private String headImg;
    /**
     * 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     */
    private String serviceTypeInfo;
    /**
     * 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，
     * 2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，
     * 4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，
     * 5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     */
    private String verifyTypeInfo;
    /**
     * 授权方公众号的原始ID
     */
    private String userName;
    /**
     * 公众号的主体名称
     */
    private String principalName;
    /**
     * 授权方公众号所设置的微信号，可能为空
     */
    private String alias;
    /**
     * 用以了解以下功能的开通状况（0代表未开通，1代表已开通）：
     */
    private String businessInfo;
    /**
     * 二维码图片的URL，开发者最好自行也进行保存
     */
    private String qrcodeUrl;
    /**
     * 授权信息
     */
    private AuthorizationInfo authorizationInfo;

    /**
     * AuthorizerInfo(获取授权方的帐号基本信息) 字符串形式
     *
     * @return AuthorizerInfo(获取授权方的帐号基本信息)字符串
     */
    @Override
    public String toString() {
        return "nickName:" + nickName + ",headImg:" + headImg + ",serviceTypeInfo:" + serviceTypeInfo + ",verifyTypeInfo:" + verifyTypeInfo + ",userName:" + userName
                + ",principalName:" + principalName + ",alias:" + alias + ",businessInfo:" + businessInfo + ",qrcodeUrl:" + qrcodeUrl + ",authorizationInfo:" + authorizationInfo;
    }

    /**
     * 获取 授权方昵称
     *
     * @return nickName 授权方昵称
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * 设置 授权方昵称
     *
     * @param nickName 授权方昵称
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    /**
     * 获取 授权方头像
     *
     * @return headImg 授权方头像
     */
    public String getHeadImg() {
        return this.headImg;
    }

    /**
     * 设置 授权方头像
     *
     * @param headImg 授权方头像
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setHeadImg(String headImg) {
        this.headImg = headImg;
        return this;
    }

    /**
     * 获取 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     *
     * @return serviceTypeInfo 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     */
    public String getServiceTypeInfo() {
        return this.serviceTypeInfo;
    }

    /**
     * 设置 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     *
     * @param serviceTypeInfo 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setServiceTypeInfo(String serviceTypeInfo) {
        this.serviceTypeInfo = serviceTypeInfo;
        return this;
    }

    /**
     * 获取 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，
     * 2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，
     * 4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，
     * 5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     *
     * @return verifyTypeInfo 授权方认证类型
     */
    public String getVerifyTypeInfo() {
        return this.verifyTypeInfo;
    }

    /**
     * 设置 授权方认证类型，-1代表未认证，0代表微信认证
     *
     * @param verifyTypeInfo 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setVerifyTypeInfo(String verifyTypeInfo) {
        this.verifyTypeInfo = verifyTypeInfo;
        return this;
    }

    /**
     * 获取 授权方公众号的原始ID
     *
     * @return userName 授权方公众号的原始ID
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置 授权方公众号的原始ID
     *
     * @param userName 授权方公众号的原始ID
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 获取 公众号的主体名称
     *
     * @return principalName 公众号的主体名称
     */
    public String getPrincipalName() {
        return this.principalName;
    }

    /**
     * 设置 公众号的主体名称
     *
     * @param principalName 公众号的主体名称
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setPrincipalName(String principalName) {
        this.principalName = principalName;
        return this;
    }

    /**
     * 获取 授权方公众号所设置的微信号，可能为空
     *
     * @return alias 授权方公众号所设置的微信号，可能为空
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * 设置 授权方公众号所设置的微信号，可能为空
     *
     * @param alias 授权方公众号所设置的微信号，可能为空
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 获取 用以了解以下功能的开通状况（0代表未开通，1代表已开通）：
     *
     * @return businessInfo 用以了解以下功能的开通状况（0代表未开通，1代表已开通）：
     */
    public String getBusinessInfo() {
        return this.businessInfo;
    }

    /**
     * 设置 用以了解以下功能的开通状况（0代表未开通，1代表已开通）：
     *
     * @param businessInfo 用以了解以下功能的开通状况（0代表未开通，1代表已开通）：
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
        return this;
    }

    /**
     * 获取 二维码图片的URL，开发者最好自行也进行保存
     *
     * @return qrcodeUrl 二维码图片的URL，开发者最好自行也进行保存
     */
    public String getQrcodeUrl() {
        return this.qrcodeUrl;
    }

    /**
     * 设置 二维码图片的URL，开发者最好自行也进行保存
     *
     * @param qrcodeUrl 二维码图片的URL，开发者最好自行也进行保存
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
        return this;
    }

    /**
     * 获取 授权信息
     *
     * @return authorizationInfo 授权信息
     */
    public AuthorizationInfo getAuthorizationInfo() {
        return this.authorizationInfo;
    }

    /**
     * 设置 授权信息
     *
     * @param authorizationInfo 授权信息
     * @return 返回 AuthorizerInfo(获取授权方的帐号基本信息)
     */
    public AuthorizerInfo setAuthorizationInfo(AuthorizationInfo authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
        return this;
    }
}

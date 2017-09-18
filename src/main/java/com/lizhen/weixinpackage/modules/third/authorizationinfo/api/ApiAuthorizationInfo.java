package com.lizhen.weixinpackage.modules.third.authorizationinfo.api;


import com.lizhen.weixinpackage.modules.third.authorizationinfo.module.AuthorizationInfo;
import com.lizhen.weixinpackage.modules.third.commonwx.module.ComponentAccessToken;
import com.lizhen.weixinpackage.modules.third.commonwx.module.ComponentVerifyTicket;

/**
 * 公众号凭据和授权信息接口
 * Created by lizhen on 2017/8/11.
 */

public interface ApiAuthorizationInfo {
    /**
     * 获取公众号的授权信息
     *
     * @param appid                微信公众号的appid
     * @param componentAccessToken 第三方的token,至设置appid即可
     * @return 授权信息
     */
    AuthorizationInfo getAuthorizationInfo(String appid, ComponentAccessToken componentAccessToken);

    /**
     * 新增授权信息
     *
     * @param componentVerifyTicket 推送信息,将第三方的appid配置即可
     * @param authorizationCode     授权code
     * @return 公众号的授权信息
     */
    AuthorizationInfo addAuthorizationInfo(ComponentVerifyTicket componentVerifyTicket, String authorizationCode);

    /**
     * 刷新公众号的授权信息
     *
     * @param componentAccessToken 第三方的token,至设置appid即可
     * @param authorizationInfo    微信公众号的授权信息
     * @return 授权信息的id
     */
    AuthorizationInfo updateAuthorizationInfo(ComponentAccessToken componentAccessToken, AuthorizationInfo authorizationInfo);

    /**
     * 删除授权信息
     *
     * @param authorizationInfo 删除授权信息
     * @return 主键
     */
    Integer deletAuthorizationInfo(AuthorizationInfo authorizationInfo);
}

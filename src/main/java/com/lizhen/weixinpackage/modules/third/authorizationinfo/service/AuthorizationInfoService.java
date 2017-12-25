package com.lizhen.weixinpackage.modules.third.authorizationinfo.service;


import com.lizhen.weixinpackage.modules.third.authorizationinfo.mapper.AuthorizationInfoDao;
import com.lizhen.weixinpackage.modules.third.authorizationinfo.module.AuthorizationInfo;
import com.lizhen.weixinpackage.modules.third.commonwx.module.ComponentAccessToken;
import com.lizhen.weixinpackage.modules.third.commonwx.module.ComponentVerifyTicket;
import com.lizhen.weixinpackage.service.thirdservice.service.ThirdService;
import com.lizhen.weixinpackage.service.weixinservice.service.AllWeiXinService;
import com.lizhen.weixinpackage.service.weixinservice.service.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 公众号的授权信息service层
 * Created by lizhen on 2017/8/11.
 */
@Service
public class AuthorizationInfoService {

    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(AuthorizationInfoService.class);
    /**
     * 授权信息的dao层
     */
    @Autowired
    private AuthorizationInfoDao authorizationInfoDao;
    /**
     * 第三方相关service层
     */
    @Autowired
    private ThirdService thirdService;
    /**
     * 引用解析的json字符串的方法
     */
    @Autowired
    private AllWeiXinService allWeiXinService;

    /**
     * 获取公众号的授权信息
     *
     * @param appid                微信公众号的appid
     * @param componentAccessToken 第三方的token,至设置appid即可
     * @return 授权信息
     */

    public AuthorizationInfo getAuthorizationInfo(String appid, ComponentAccessToken componentAccessToken) {
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setAuthorizerAppid(appid);
        Long time = new Date().getTime() / 1000;
        //TODO 数据库中获取数据
        authorizationInfo = authorizationInfoDao.getAuthorizationInfo(authorizationInfo);
        if (null != authorizationInfo) {
            Long authorizationInfoTime = authorizationInfo.getAuthorizationInfoTime();
            int expiresIn = Integer.parseInt(authorizationInfo.getExpiresIn());
            //如果失效,重新获取
            if (time - authorizationInfoTime > expiresIn) {
                authorizationInfo = updateAuthorizationInfo(componentAccessToken, authorizationInfo);
                return authorizationInfo;
            }
            return authorizationInfo;
        }
        return null;
    }


    /**
     * 新增授权信息
     *
     * @param componentVerifyTicket 推送信息,将第三方的appid配置即可
     * @param authorizationCode     授权code
     * @return 公众号的授权信息
     */

    public AuthorizationInfo addAuthorizationInfo(ComponentVerifyTicket componentVerifyTicket, String authorizationCode) {
        ComponentAccessToken componentAccessToken = thirdService.getComponentAccessToken(componentVerifyTicket);
        log.info("获取授权信息 参数获取第三方ComponentAccessToken=" + componentAccessToken.toString() + ",授权码authorizationCode=" + authorizationCode);
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        Long time = new Date().getTime() / 1000;
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + componentAccessToken.getComponentAccessToken();
        String jsonData = "";
        if (null != componentAccessToken) {
            jsonData += "{";
            jsonData += "\"component_appid\":" + "\"" + componentAccessToken.getAppid() + "\",";
            jsonData += "\"authorization_code\":" + "\"" + authorizationCode + "\"}";
            log.debug("获取授权信息的json数据======" + jsonData);
        }

        try {
            String sendPost = HttpUtils.sendPostJson(url, jsonData);
            String authorizationInfo1 = allWeiXinService.pareJsonDate(sendPost, "authorization_info");
            String authorizerAppid = allWeiXinService.pareJsonDate(authorizationInfo1, "authorizer_appid");
            String authorizerAccessToken = allWeiXinService.pareJsonDate(authorizationInfo1, "authorizer_access_token");
            String expiresIn = allWeiXinService.pareJsonDate(authorizationInfo1, "expires_in");
            String authorizerRefreshToken = allWeiXinService.pareJsonDate(authorizationInfo1, "authorizer_refresh_token");
            String funcInfo = allWeiXinService.pareJsonDate(authorizationInfo1, "func_info");
            authorizationInfo.setAuthorizerAppid(authorizerAppid).setAuthorizerRefreshToken(authorizerRefreshToken)
                    .setAuthorizerAccessToken(authorizerAccessToken).setExpiresIn(expiresIn).setFuncInfo(funcInfo).setAuthorizationInfoTime(time - 60);
            //TODO 数据库中获取数据
            AuthorizationInfo authorizationInfo2 = authorizationInfoDao.getAuthorizationInfo(authorizationInfo);
            Integer integer = null;
            if (null != authorizationInfo2) {
                //TODO 数据库中获取数据
                 integer = authorizationInfoDao.updateAuthorizationInfo(authorizationInfo);
                log.info("之前授权过,进行修改" + authorizationInfo.toString());
                return authorizationInfo;
            }
            //TODO 数据库中获取数据
             integer = authorizationInfoDao.addAuthorizationInfo(authorizationInfo);
            log.info("新增授权id" + integer + ",新增授权信息  = " + authorizationInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorizationInfo;

    }


    /**
     * 刷新公众号的授权信息
     *
     * @param componentAccessToken 第三方的token,至设置appid即可
     * @param authorizationInfo    微信公众号的授权信息
     * @return 授权信息的id
     */

    public AuthorizationInfo updateAuthorizationInfo(ComponentAccessToken componentAccessToken, AuthorizationInfo authorizationInfo) {
        Long time = new Date().getTime() / 1000;
        log.info("获取（刷新）授权公众号或小程序的接口调用凭据（令牌）componentAccessToken =" + componentAccessToken.toString()
                + ",AuthorizationInfo=" + authorizationInfo.toString());
        String url = "https:// api.weixin.qq.com /cgi-bin/component/api_authorizer_token?component_access_token=" + componentAccessToken.getComponentAccessToken();

        String jsonData = "";
        if (null != componentAccessToken) {
            jsonData += "{";
            jsonData += "\"component_appid\":" + "\"" + componentAccessToken.getAppid() + "\",";
            jsonData += "\"authorizer_appid\":" + "\"" + authorizationInfo.getAuthorizerAppid() + "\",";
            jsonData += "\"authorizer_refresh_token\":" + "\"" + authorizationInfo.getAuthorizerRefreshToken() + "\",}";
            log.debug("刷新授权公众号的json数据======" + jsonData);
        }
        try {
            String sendPost = HttpUtils.sendPostJson(url, jsonData);
            String authorizerAccessToken = allWeiXinService.pareJsonDate(sendPost, "authorizer_access_token");
            String expiresIn = allWeiXinService.pareJsonDate(sendPost, "expires_in");
            String authorizerRefreshToken = allWeiXinService.pareJsonDate(sendPost, "authorizer_refresh_token");
            authorizationInfo.setAuthorizerRefreshToken(authorizerRefreshToken).setAuthorizerAccessToken(authorizerAccessToken).
                    setExpiresIn(expiresIn).setAuthorizationInfoTime(time - 600);
            //TODO 数据库中获取数据
            Integer integer = authorizationInfoDao.updateAuthorizationInfo(authorizationInfo);
            log.debug("刷新令牌保存成功" + integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取（刷新）授权公众号或小程序的接口调用凭据" + authorizationInfo.toString());
        return authorizationInfo;

    }


    /**
     * 删除授权信息
     *
     * @param authorizationInfo 删除授权信息
     * @return 主键
     */

    public Integer deletAuthorizationInfo(AuthorizationInfo authorizationInfo) {
        //TODO 数据库中获取数据
        return authorizationInfoDao.deletAuthorizationInfo(authorizationInfo);
    }

}

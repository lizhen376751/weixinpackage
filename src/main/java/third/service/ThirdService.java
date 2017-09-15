package third.service;


import com.alibaba.fastjson.JSONObject;
import modules.third.authorizationinfo.module.AuthorizationInfo;
import modules.third.commonwx.module.*;
import modules.weixin.weixinconfig.module.WeiXinConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.weixin.http.service.AllWeiXinService;
import service.weixin.http.service.HttpUtils;
import service.weixin.http.util.RedisUtil;
import third.aes.AesException;
import third.aes.WXBizMsgCrypt;
import third.authorizationinfo.service.AuthorizationInfoService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方开发平台
 * Created by lizhen on 2017/7/17.
 */
@Service
public class ThirdService {

    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(ThirdService.class);
    /**
     * redis util
     */
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 引用解析的json字符串的方法
     */
    @Autowired
    private AllWeiXinService allWeiXinService;

    /**
     * 授权公众号信息
     */
    @Autowired
    private AuthorizationInfoService authorizationInfoService;
//========以 下 是加密解密======================================================================================================================

    /**
     * 消息解密
     *
     * @param aesParams 解密所需要的参数
     * @return 解密后的xml
     */

    public String decrypt(AESParams aesParams) {
        log.info("解密传过来的参数为=" + aesParams.toString());
        WXBizMsgCrypt pc = null;
        String xml = "";
        try {
            pc = new WXBizMsgCrypt(aesParams.getToken(), aesParams.getEncodingAesKey(), aesParams.getAppId());
            //解密
            xml = pc.decryptMsg(aesParams.getMsgSignature(), aesParams.getTimestamp(), aesParams.getNonce(), aesParams.getXml());
        } catch (AesException e) {
            e.printStackTrace();
            log.info("消息解密失败原因:" + e.getMessage());
            xml = e.getMessage();
        }
        return xml;
    }

    /**
     * 消息加密
     *
     * @param aesParams 解密所需要的参数
     * @return 解密后的xml
     */

    public String encrypt(AESParams aesParams) {
        log.info("加密传过来的参数为=" + aesParams.toString());
        WXBizMsgCrypt pc = null;
        String xml = "";
        try {
            pc = new WXBizMsgCrypt(aesParams.getToken(), aesParams.getEncodingAesKey(), aesParams.getAppId());
            //加密
            xml = pc.encryptMsg(aesParams.getXml(), aesParams.getTimestamp(), aesParams.getNonce());
        } catch (AesException e) {
            e.printStackTrace();
            log.info("消息加密失败原因:" + e.getMessage());
            xml = e.getMessage();
        }
        return xml;
    }
    //=========以 上 是加密解密======================================================================================================================


    //==========以 下 是ticket=====================================================================================================================

    /**
     * 在解密后的xml中获取ticket,并保存Ticket
     *
     * @param xml 解密后的xml
     * @return 协议的ticket
     */

    public ComponentVerifyTicket processAuthorizationEvent(String xml) {
        log.info("十分钟发过来协议 在解密后的xml中获取ticket,xml = " + xml);
        Document doc;
        ComponentVerifyTicket entity = new ComponentVerifyTicket();
        try {
            //TODO 获取第三方开发平台的appid和appsecret
            WeiXinConfig third = new WeiXinConfig();
            doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement();
            String ticket = rootElt.elementText("ComponentVerifyTicket");
            String appId = rootElt.elementText("AppId");
            String createTime = rootElt.elementText("CreateTime");
            String infoType = rootElt.elementText("InfoType");
            Long time = new Date().getTime() / 1000;
            log.info("ticket解析后 ticket=" + ticket + ",appId=" + appId + ",createTime=" + createTime + ",infoType=" + infoType);
            if (ticket != null && !"".equals(ticket) && !"null".equals(ticket)) {
                entity.setAppId(appId).setComponentVerifyTicket(ticket).setCreateTime(createTime).setInfoType(infoType).
                        setAppsecret(third.getAppserect()).setTicketTime(time);
                log.info("redis保存ticket...");
                //TODO 保存ticket
                return entity;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        log.info("十分钟发过来协议 在解密后的xml中获取ticket,并保存Ticket = " + entity.toString());
        return null;
    }


    /**
     * 获取第三方开发平台的token
     *
     * @param componentVerifyTicket ticket协议的实体类
     * @return 第三方token的实体类
     */
    public ComponentAccessToken getComponentAccessToken(ComponentVerifyTicket componentVerifyTicket) {
        String componentVerifyTicket1 = componentVerifyTicket.getComponentVerifyTicket();
        Long time = new Date().getTime() / 1000;
        String appId = componentVerifyTicket.getAppId();
        String appSecret = componentVerifyTicket.getAppsecret();
        //token 2小时有效期
        int seconds = 2 * 60 * 60;

        String key = appId + ":token";
        //TODO 获取第三方的token
        String tokenStr = "";

        if (tokenStr != null) {
            ComponentAccessToken componentAccessToken = JSONObject.parseObject(tokenStr, ComponentAccessToken.class);
            Long tokenTime = componentAccessToken.getTokenTime();
            int expiresIn1 = Integer.parseInt(componentAccessToken.getExpiresIn());
            log.info("不为空的情况下redis缓存中获取token" + componentAccessToken.toString() + "保存时间:" + tokenTime.toString());
            if (time - tokenTime < expiresIn1 || componentVerifyTicket.getComponentVerifyTicket() == null || "".equals(componentVerifyTicket.getComponentVerifyTicket())) {
                return componentAccessToken;
            }
        }
        if (componentVerifyTicket1 == null || "".equals(componentVerifyTicket1) || "null".equals(componentVerifyTicket1)) {
            log.debug("redis里面没有获取到token,返回空.....");
            return null;
        }
        log.info("获取第三方开发平台的token 传过来的Ticket = " + componentVerifyTicket.toString());
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
        String jsonData = "";
        if (null != componentVerifyTicket) {
            jsonData += "{";
            jsonData += "\"component_appid\":" + "\"" + componentVerifyTicket.getAppId() + "\",";
            jsonData += "\"component_appsecret\":" + "\"" + componentVerifyTicket.getAppsecret() + "\",";
            jsonData += "\"component_verify_ticket\":" + "\"" + componentVerifyTicket.getComponentVerifyTicket() + "\",}";
            log.debug("获取token时json的数据封装======" + jsonData);
        }

        ComponentAccessToken componentAccessToken = new ComponentAccessToken();
        try {
            String token = HttpUtils.sendPostJson(url, jsonData);
            log.info("获取第三方的token返回的结果为====" + token);
            String componentAccessToken1 = allWeiXinService.pareJsonDate(token, "component_access_token");
            String expiresIn = allWeiXinService.pareJsonDate(token, "expires_in");
            componentAccessToken.setAppid(componentVerifyTicket.getAppId());
            componentAccessToken.setComponentAccessToken(componentAccessToken1);
            componentAccessToken.setExpiresIn(expiresIn);
            componentAccessToken.setTokenTime(time - 600); //提前十分钟获取
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取第三方开发平台的token = " + componentAccessToken.toString());
        String accessToken = componentAccessToken.getComponentAccessToken();
        if (accessToken != null && !"".equals(accessToken) && !"null".equals(accessToken)) {
            log.debug("redis保存第三方token开始 = ");
            //TODO 保存第三方的token
        }
        return componentAccessToken;
    }

    /**
     * 获取预授权码
     *
     * @param componentAccessToken token实体类
     * @return 预授权码
     */

    public PreAuthCode getPreAuthCode(ComponentAccessToken componentAccessToken) {
        log.info("获取预授权码 参数第三方开发平台的token = " + componentAccessToken.toString());
        Long time = new Date().getTime() / 1000;
        PreAuthCode preAuthCode = new PreAuthCode();
        String appId = componentAccessToken.getAppid();
        String token = componentAccessToken.getComponentAccessToken();
        //token 20分钟有效期
        int seconds = 30 * 60;

        String key = appId + ":preauthcode";
        //TODO 获取第三方的预授权码
        String authStr = "";

        if (null != authStr) {
            preAuthCode = JSONObject.parseObject(authStr, PreAuthCode.class);
            Long preAuthCodeTime = preAuthCode.getPreAuthCodeTime();
            int expiresIn = Integer.parseInt(preAuthCode.getExpiresIn());
            log.debug("不为空的情况下在redis里面获取预授权码" + preAuthCode.toString() + ",保存时间" + preAuthCodeTime.toString());
            if (time - preAuthCodeTime < expiresIn || null == token || "".equals(token)) {
                //提前一分钟进行获取,如果需要token,如果为空便是通过其他途径获取...
                return preAuthCode;
            }
        }
        if (null == token || "".equals(token) || "null".equals(token)) {
            return null;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + token;

        String jsonData = "";
        if (null != componentAccessToken) {
            jsonData += "{";
            jsonData += "\"component_appid\":" + "\"" + appId + "\"}";
            log.debug("获取预授权码的json数据======" + jsonData);
        }
        String preAuthCode1 = "";
        try {
            String sendPost = HttpUtils.sendPostJson(url, jsonData);
            preAuthCode1 = allWeiXinService.pareJsonDate(sendPost, "pre_auth_code");
            String expiresIn = allWeiXinService.pareJsonDate(sendPost, "expires_in");
            preAuthCode.setAppid(componentAccessToken.getAppid());
            preAuthCode.setPreAuthCode(preAuthCode1);
            preAuthCode.setExpiresIn(expiresIn);
            preAuthCode.setPreAuthCodeTime(time - 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (preAuthCode1 != null && !"".equals(preAuthCode1) && !"null".equals(preAuthCode1)) {
            log.debug("redis保存预授权码开始 .... ");
            //TODO 保存预授权码
        }

        log.info("获取预授权码 = " + preAuthCode.toString());
        return preAuthCode;
    }


    //=========以 上 是ticket 预授权码以及第三方的token=============================================================================================================================


//=========以 下 授权公众号的相关信息=============================================================================================================================

    /**
     * 获取公众号的授权信息
     *
     * @param appid                公众号的appid
     * @param componentAccessToken 第三方的token
     * @return 授权信息
     */
    public AuthorizationInfo getWeiXinToken(String appid, ComponentAccessToken componentAccessToken) {
        AuthorizationInfo authorizationInfo = authorizationInfoService.getAuthorizationInfo(appid, componentAccessToken);
        return authorizationInfo;
    }


    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
     *
     * @param componentVerifyTicket 第三方appid
     * @param authorizationCode     授权的公众的授权码
     * @return 授权相关的信息
     */
    public AuthorizationInfo getAuthorizationInfo(ComponentVerifyTicket componentVerifyTicket, String authorizationCode) {
        AuthorizationInfo authorizationInfo = authorizationInfoService.addAuthorizationInfo(componentVerifyTicket, authorizationCode);
        return authorizationInfo;
    }

    /**
     * 获取授权方的帐号基本信息
     *
     * @param componentAccessToken 第三方的token实体类
     * @param authorizerAppid      appid
     * @return 授权方的帐号基本信息
     */

    public AuthorizerInfo getAuthorizerInfo(ComponentAccessToken componentAccessToken, String authorizerAppid) {
        log.info("获取授权方的帐号基本信息ComponentAccessToken=" + componentAccessToken.toString() + ",授权方的appid=" + authorizerAppid);
        AuthorizerInfo authorizerInfo = new AuthorizerInfo();
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=" + componentAccessToken.getComponentAccessToken();
        Map<String, String> params = new HashMap<String, String>();
        params.put("component_appid", componentAccessToken.getAppid());
        params.put("authorizer_appid", authorizerAppid);
        try {
            String sendPost = HttpUtils.sendPost(url, params);
            String authorizationInfo1 = allWeiXinService.pareJsonDate(sendPost, "authorizer_info");
            String nickName = allWeiXinService.pareJsonDate(authorizationInfo1, "nick_name");
            String headImg = allWeiXinService.pareJsonDate(authorizationInfo1, "head_img");
            String serviceTypeInfo = allWeiXinService.pareJsonDate(authorizationInfo1, "service_type_info");
            String verifyTypeInfo = allWeiXinService.pareJsonDate(authorizationInfo1, "verify_type_info");
            String userName = allWeiXinService.pareJsonDate(authorizationInfo1, "user_name");
            String principalName = allWeiXinService.pareJsonDate(authorizationInfo1, "principal_name");
            String alias = allWeiXinService.pareJsonDate(authorizationInfo1, "alias");
            String businessInfo = allWeiXinService.pareJsonDate(authorizationInfo1, "business_info");
            String qrcodeUrl = allWeiXinService.pareJsonDate(authorizationInfo1, "qrcode_url");
            String authorizerInfo1 = allWeiXinService.pareJsonDate(sendPost, "authorization_info");
            String appid = allWeiXinService.pareJsonDate(authorizerInfo1, "appid");
            String funcInfo = allWeiXinService.pareJsonDate(authorizerInfo1, "func_info");
            authorizerInfo.setNickName(nickName);
            authorizerInfo.setAlias(alias);
            authorizerInfo.setBusinessInfo(businessInfo);
            authorizerInfo.setHeadImg(headImg);
            authorizerInfo.setPrincipalName(principalName);
            authorizerInfo.setQrcodeUrl(qrcodeUrl);
            authorizerInfo.setServiceTypeInfo(serviceTypeInfo);
            authorizerInfo.setUserName(userName);
            authorizerInfo.setVerifyTypeInfo(verifyTypeInfo);
            authorizationInfo.setAuthorizerAppid(appid);
            authorizationInfo.setFuncInfo(funcInfo);
            authorizerInfo.setAuthorizationInfo(authorizationInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取授权方的帐号基本信息authorizerInfo=" + authorizerInfo.toString());
        return authorizerInfo;
    }

}

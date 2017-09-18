package service.weixinservice.service;


import modules.weixin.parammodule.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.weixinservice.accesstoken.service.AccessTokenService;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.UUID;


/**
 * 签名算法
 * 用户微信前端页面的jssdk配置使用
 * Created by lizhen on 2017/4/24.
 */
@Service
public class JSSDKConfig {
    /**
     * token的service
     */
    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * @param hash ..
     * @return 字符串
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 创建uuid
     *
     * @return 字符串
     */
    private static String createnoncestr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 前端jssdk页面配置需要用到的配置参数
     *
     * @param url       前段页面传入的url(动态的,当前网页的URL，不包含#及其后面部分）
     * @param appid     微信的appid
     * @param appSecret 微信的appSecret
     * @return map 签名参数
     * @throws Exception 异常
     */
    public HashMap<String, String> jsSDKSign(String appid, String appSecret, String url) throws Exception {
        AccessToken tokengetTicket = accessTokenService.getAccessToken(appid, appSecret);
        //随机字符串
        String noncestr = createnoncestr();
        //timestamp（时间戳）
        String timestamp = String.valueOf(tokengetTicket.getCreateTime());
        //有效的jsapi_ticket
        String jsapiticket = tokengetTicket.getTicket();
        // 对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）并且
        // 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1
        String string1 = "jsapi_ticket=" + jsapiticket + "&noncestr=" + noncestr
                + "&timestamp=" + timestamp + "&url=" + url;
        //对string1作sha1加密,字段名和字段值都采用原始值，不进行URL 转义。
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());
        HashMap<String, String> jssdk = new HashMap<String, String>();
        jssdk.put("appId", appid);
        jssdk.put("timestamp", timestamp);
        jssdk.put("nonceStr", noncestr);
        jssdk.put("signature", signature);
        return jssdk;

    }

}
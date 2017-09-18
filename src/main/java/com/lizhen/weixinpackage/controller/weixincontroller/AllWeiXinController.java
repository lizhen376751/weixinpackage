package controller.weixinservice;


import modules.third.message.module.CuatomerNews;
import modules.weixin.menu.Menu;
import modules.weixin.parammodule.*;
import modules.weixin.weixinmessage.ParamSendWeChat;
import modules.weixin.weixinmessage.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.weixinservice.accesstoken.service.AccessTokenService;
import service.weixinservice.service.AllWeiXinService;
import service.weixinservice.service.JSSDKConfig;
import service.weixinservice.service.MsgDispatcher;
import service.weixinservice.service.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信通讯相关所有的入口
 * Created by lizhen on 2017/4/22.
 */
@Controller
public class AllWeiXinController {

    /**
     * 引入微信服务所有方法
     */
    @Autowired
    private AllWeiXinService weChatTask;
    /**
     * 引入获取js签名的服务
     */
    @Autowired
    private JSSDKConfig jssdkConfig;
    /**
     * 微信公众号的token
     */
    @Autowired
    private AccessTokenService accessTokenService;


    /**
     * 将token、timestamp、nonce三个参数进行字典序排序
     *
     * @param signature 微信加密签名
     * @param timestamp 日期戳
     * @param nonce     随机数字
     * @param token     开发者的token
     * @return true代表匹配, false代表不匹配
     */
    @RequestMapping("/checkSignature")
    public boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        boolean b = SignUtil.checkSignature(signature, timestamp, nonce, token);
        return b;
    }

    /**
     * 生成菜单
     *
     * @param menu      菜单
     * @param appid     appid
     * @param appSecret appSecret
     */

    public String createMenu(Menu menu, String appid, String appSecret) {
        String menu1 = weChatTask.createMenu(menu, appid, appSecret);
        return menu1;
    }

    /**
     * 获取开发者的token
     *
     * @param appid     appid
     * @param appSecret appSecret
     * @return 开发者的token tokenget ticket
     */

    public AccessToken getTokengetTicket(String appid, String appSecret) {
        AccessToken tokengetTicket = accessTokenService.getAccessToken(appid, appSecret);
        return tokengetTicket;
    }

    /**
     * 用店铺编码或者联盟编码获取开发者的token
     *
     * @return 开发者的token tokenget ticket
     */

    public AccessToken getTokenByCode(String appId, String xAppSecret) {
        AccessToken tokengetTicket = accessTokenService.getAccessToken(appId, xAppSecret);
        return tokengetTicket;
    }

    /**
     * 获取网页授权access_token及用户的openID
     *
     * @param appid     appid
     * @param appSecret appSecret
     * @param code      微信code
     * @return WeixinOauth2Token
     * @throws Exception Exception
     */

    public OauthOpenIdToken getOauthAccessToken(String code, String appid, String appSecret) {
        OauthOpenIdToken oauthAccessToken = weChatTask.getOauthAccessToken(code, appid, appSecret);
        return oauthAccessToken;

    }

    /**
     * 利用code获取用户的基本信息
     *
     * @param code   权限code
     * @param appid  微信appId
     * @param secret 微信appSecret
     * @return 微信用户的基本信息
     * @throws Exception 异常
     */

    public WeiXinUserInfo getWeiXinUserInfo(String code, String appid, String secret) {
        WeiXinUserInfo weiXinUserInfo = weChatTask.getWeiXinUserInfo(code, appid, secret);
        return weiXinUserInfo;
    }

    /**
     * 通过OpenID来获取用户基本信息
     *
     * @param shopCode 店铺编码
     * @param lmcode   联盟编码
     * @param openid   openid
     * @return 微信用户
     */

    public WeiXinUserInfo getWeiXinUserInfoByOpenid(String shopCode, String lmcode, String openid) {
        WeiXinUserInfo weiXinUserInfo = weChatTask.getWeiXinUserInfoByOpenid(shopCode, lmcode, openid);
        return weiXinUserInfo;
    }

    /**
     * 接收微信端消息处理并做分发
     */

    public String receivemessage(Map<String, String> map) {


        String message = MsgDispatcher.processMessage(map); //进入消息处理
        return message;

    }

    /**
     * 模板消息的发送
     *
     * @param appid     微信appid
     * @param appSecret appsecret
     * @param template  微信的消息模板
     * @return template
     */

    public String sendTemplateMsg(String appid, String appSecret, Template template) {
        String b = weChatTask.sendTemplateMsg(appid, appSecret, template);
        return b;
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

    public HashMap<String, String> jsSDKSign(String appid, String appSecret, String url) {
        HashMap<String, String> stringStringHashMap = null;
        try {
            stringStringHashMap = jssdkConfig.jsSDKSign(appid, appSecret, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringStringHashMap;
    }

    /**
     * 微信图文消息群发(仅限单条)
     *
     * @param paramSendWeChat 接口所需要的参数
     * @return
     */

    public String sendGroupMessage(ParamSendWeChat paramSendWeChat) {
        return weChatTask.sendGroupMessage(paramSendWeChat);
    }

    /**
     * 微信支付统一下单接口
     *
     * @param sweepPay 请求生成微信二维码的url
     * @return 微信二维码的url
     */

    public String weixinpay(SweepPay sweepPay) {
        return weChatTask.weixinpay(sweepPay);
    }

    /**
     * 客服接口-发送消息
     *
     * @param token 第三方开发平台的token
     * @param json  消息转换为json类型
     * @return 发送成功后的回调
     */

    public String customerSmsSend(String token, String json) {
        return weChatTask.customerSmsSend(token, json);
    }

    /**
     * 客服发送图文消息(电子优惠券的详情url做了处理)
     *
     * @param cuatomerNews 图文消息
     * @return success或者错误原因
     */

    public String customerSendCard(String appId, String xAppSecret, CuatomerNews cuatomerNews) {
        return weChatTask.customerSendCard(appId, xAppSecret, cuatomerNews);
    }

    /**
     * 生成微信的临时二维码
     *
     * @param ticket 需要传入店铺编码或者联盟编码,以及需要传入的参数
     * @return ticket获取url即可
     */

    public Ticket getTicket(Ticket ticket) {
        return weChatTask.getTicket(ticket);
    }


}

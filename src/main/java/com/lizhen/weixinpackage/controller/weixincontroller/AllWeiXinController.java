package com.lizhen.weixinpackage.controller.weixincontroller;


import com.lizhen.weixinpackage.modules.weixin.accesstoken.service.AccessTokenService;
import com.lizhen.weixinpackage.modules.weixin.menu.Menu;
import com.lizhen.weixinpackage.modules.weixin.parammodule.*;
import com.lizhen.weixinpackage.modules.weixin.weixinmessage.ParamSendWeChat;
import com.lizhen.weixinpackage.modules.weixin.weixinmessage.Template;
import com.lizhen.weixinpackage.service.weixinservice.service.AllWeiXinService;
import com.lizhen.weixinpackage.service.weixinservice.service.JSSDKConfig;
import com.lizhen.weixinpackage.service.weixinservice.service.MsgDispatcher;
import com.lizhen.weixinpackage.service.weixinservice.service.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 接口文档 : https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     */

    public String weixinpay(SweepPay sweepPay) {
        return weChatTask.weixinpay(sweepPay);
    }

    /**
     * 微信异步通知地址
     * @param request 请求
     * @param response 相应
     * 接口文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
     */
    public void weixinotify(HttpServletRequest request, HttpServletResponse response){
         weChatTask.payNotifyUrl(request,response);
    }

    /**
     * 企业向个人付款
     * @param transfer 企业付款实体类
     * @return 付款成功后的订单号或者付款失败后的错误原因
     */
    public String transfers(Transfer transfer){
        String transfers = weChatTask.transfers(transfer);
        return transfers;
    }

    /**
     * 查询企业付款接口
     * @param querytransfer 查询所需要的参数
     * @return 查询到的转账信息
     */
    public ReturnTransfer querytransfers(Querytransfer querytransfer) {
        ReturnTransfer querytransfers = weChatTask.querytransfers(querytransfer);
        return querytransfers;
    }
    /**
     * 客服接口-发送消息
     *
     *
     * @param appId 公众号的appid
     * @param appSecret  公众号的appsecret
     * @param toJSONString 消息实体类 (无论什么类型的消息可以转换成json类型的)
     * @return 发送成功后的回调
     */

    public String customerSmsSend(String appId, String appSecret, String toJSONString) {
        return weChatTask.customerSmsSend(appId,appSecret, toJSONString);
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

package com.lizhen.weixinpackage.service.weixinservice.service;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.weixinpackage.controller.weixincontroller.AllWeiXinController;
import com.lizhen.weixinpackage.modules.third.message.module.CuatomerNews;
import com.lizhen.weixinpackage.modules.weixin.accesstoken.service.AccessTokenService;
import com.lizhen.weixinpackage.modules.weixin.http.HttpMethod;
import com.lizhen.weixinpackage.modules.weixin.http.WeixinActionMethodDefine;
import com.lizhen.weixinpackage.modules.weixin.http.WeixinBaseParamter;
import com.lizhen.weixinpackage.modules.weixin.menu.Menu;
import com.lizhen.weixinpackage.modules.weixin.parammodule.*;
import com.lizhen.weixinpackage.modules.weixin.weixinmessage.*;
import com.lizhen.weixinpackage.service.weixinservice.util.PayCommonUtil;
import com.lizhen.weixinpackage.service.weixinservice.util.XMLUtil;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;


/**
 * 微信基本入口的逻辑包装
 * 关于微信的服务
 */
@Service
public class AllWeiXinService {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(AllWeiXinService.class);
    /**
     * 引入图片上传和消息素材上传服务
     */
    @Autowired
    private FileUpload fileUpload;

    /**
     * token的service层
     */
    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 微信相关请求
     */
    @Autowired
    private AllWeiXinController allWeiXinRquest;

    /**
     * TODO 需要安装redis 利用redis获取token
     *
     * @param appid     微信的appid
     * @param appSecret 微信的appsecret
     * @return token
     */
//    public static AccessToken getTokenTicket(String appid, String appSecret) {
    //1.首先在redis查询是否存有token(直接查询店铺编码)
    //2.如果没有直接获取存放在redis里面
    //3.如果有的话直接判断时间,是否过期,
    // 3.1过期的话重新获取,将之前的删除,并重新存入
    //3.2如果没有过期直接取出来用
//        AccessToken tokengetTicket = new AccessToken();
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        List<String> rsmap = jedis.hmget(appid, "token", "ticket", "expiresIn", "createTime");
//        String token = rsmap.get(0);
//        String ticket = rsmap.get(1);
//        if (token == null || token.equals("")) {
//            tokengetTicket = getTokenTicket(appid, appSecret);
//            Map map = MapUtils.toMap(tokengetTicket);
//            jedis.hmset(appid, map);
//        } else {
//            int expiresIn = Integer.parseInt(rsmap.get(2));
//            Long createTime = Long.parseLong(rsmap.get(3));
//            //获取当前时间
//            long time = new Date().getTime() / 1000;
//            //判断是否在有效时间之内
//            if (time - createTime > expiresIn) {
//                //大于有效时间需要重新获取,首先删除记录,然后存储
//                jedis.hlen(appid);
//                tokengetTicket = getTokenTicket(appid, appSecret);
//                Map map = MapUtils.toMap(tokengetTicket);
//                jedis.hmset(appid, map);
//            } else {
//                tokengetTicket.setExpiresIn(expiresIn);
//                tokengetTicket.setTicket(ticket);
//                tokengetTicket.setToken(token);
//            }
//
//        }
//        return tokengetTicket;
//    }
    //配置验证

    /**
     * 签名来验证配置
     * @param signature 按一定规则生成的签名字符串
     * @param timestamp 日期戳
     * @param nonce 随机字符串
     * @param token 令牌
     * @return true 或者 false
     */
    public boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        boolean b = SignUtil.checkSignature(signature, timestamp, nonce, token);
        return b;
    }

    /**
     * 获取开发者的token和jssdk的jsapiticket
     *
     * @param appid     appid
     * @param appSecret appSecret
     * @return 开发者的token
     * @throws IOException        网络异常
     * @throws URISyntaxException 异常
     */
    public AccessToken getTokengetTicket(String appid, String appSecret) {
        AccessToken accessToken = accessTokenService.getAccessToken(appid, appSecret);
        return accessToken;

    }

    /**
     * 生成菜单
     *
     * @param menu      菜单
     * @param appid     appid
     * @param appSecret appSecret
     * @return 成功或者失败
     */
    public String createMenu(Menu menu, String appid, String appSecret) {
        //需要token,(appid,sercert)
        String flag = "";
        AccessToken tokengetTicket = null;
        try {
            tokengetTicket = this.getTokengetTicket(appid, appSecret);
            String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", tokengetTicket.getToken());
            String jsonMenu = JSONObject.toJSONString(menu);
            String jsonResult = HttpUtils.sendPostJson(url, jsonMenu);
            if (jsonResult != null) {
                int errorCode = Integer.parseInt(pareJsonDate(jsonResult, "errcode"));
                String errorMessage = pareJsonDate(jsonResult, "errmsg");
                if (errorCode == 0) {
                    flag = "创建成功";
                } else {
                    log.info("创建菜单成功:" + errorCode + "," + errorMessage);
                    flag = "创建菜单失败:" + errorCode + "," + errorMessage;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
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
        //获取token
        AccessToken tokengetTicket = this.getTokengetTicket(appid, appSecret);
        String flag = "";
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", tokengetTicket.getToken());

        try {
            String jsonResult = HttpUtils.sendPostJson(requestUrl, template.toJSON());
            //TODO 模板消息的处理
            if (jsonResult != null) {
                int errorCode = Integer.parseInt(pareJsonDate(jsonResult, "errcode"));
                String errorMessage = pareJsonDate(jsonResult, "errmsg");
                if (errorCode == 0) {
                    flag = "模板消息发送成功";
                } else {
                    log.info("模板消息发送失败:" + errorCode + "," + errorMessage);
                    flag = "模板消息发送失败:" + errorCode + "," + errorMessage;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = e.getMessage();
        }
        return flag;

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
        try {
            Map<String, String> params = new HashMap<>();
            params.put("appid", appid);
            params.put("secret", appSecret);
            params.put("code", code);
            params.put("grant_type", "authorization_code");
            String request = HttpUtils.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
            log.info("获取openid=========================" + request);
            OauthOpenIdToken oauthOpenIdTokennew = new OauthOpenIdToken();
            String expiresin = pareJsonDate(request, "expires_in");
            if (null != expiresin && !"".equals(expiresin)) {
                oauthOpenIdTokennew.setExpiresIn(Integer.parseInt(expiresin));
            }
            oauthOpenIdTokennew.setOpenId(pareJsonDate(request, "openid"));
            oauthOpenIdTokennew.setScope(pareJsonDate(request, "scope"));
            oauthOpenIdTokennew.setRefreshToken(pareJsonDate(request, "refresh_token"));
            oauthOpenIdTokennew.setAccessToken(pareJsonDate(request, "access_token"));
            return oauthOpenIdTokennew;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO 获取基本信息
        return null;
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

        try {
            //1.获取开发者的access_token
            AccessToken tokengetTicket = this.getTokengetTicket(appid, secret);
            String token = tokengetTicket.getToken();
            //2.获取openid
            OauthOpenIdToken oauthAccessToken = this.getOauthAccessToken(code, appid, secret);
            String openId = oauthAccessToken.getOpenId();
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openId+"&lang=zh_CN";
            String s = HttpUtils.sendGet(url,null);
            //获取用户的昵称
            WeiXinUserInfo weiXinUserInfo = jsonToEntry(s);
            return weiXinUserInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        AccessToken tokenByCode = allWeiXinRquest.getTokenByCode(shopCode, lmcode);
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + tokenByCode.getToken() + "&openid=" + openid + "&lang=zh_CN";
        try {
            String s = HttpUtils.sendGet(url, null);
            //获取用户的昵称
            WeiXinUserInfo weiXinUserInfo = jsonToEntry(s);
            return weiXinUserInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转换微信用户信息
     *
     * @param s 请求用户信息获取的json字符串
     * @return 微信用户信息
     */
    public WeiXinUserInfo jsonToEntry(String s) {
        WeiXinUserInfo weiXinUserInfo = new WeiXinUserInfo()
                .setCity(pareJsonDate(s, "city"))
                .setCountry(pareJsonDate(s, "country"))
                .setGroupid(pareJsonDate(s, "groupid"))
                .setHeadimgurl(pareJsonDate(s, "headimgurl"))
                .setLanguage(pareJsonDate(s, "language"))
                .setNickname(pareJsonDate(s, "nickname"))
                .setOpenid(pareJsonDate(s, "openid"))
                .setProvince(pareJsonDate(s, "province"))
                .setRemark(pareJsonDate(s, "remark"))
                .setSex(pareJsonDate(s, "sex"))
                .setSubscribe(pareJsonDate(s, "subscribe"))
                .setSubscribetime(pareJsonDate(s, "subscribe_time"))
                .setUnionid(pareJsonDate(s, "unionid"));
        return weiXinUserInfo;
    }


    /**
     * 获取jsticket
     *
     * @param appid     微信的appid
     * @param appSecret 微信的 appSecret
     */
    public void getTicket(String appid, String appSecret) {
        //获取token实体
        AccessToken tokengetTicket = getTokengetTicket(appid, appSecret);
        //在token实体中获取日期戳
        long createTime = tokengetTicket.getCreateTime();
        //在token实体中获取token
        String token = tokengetTicket.getToken();
        WeixinActionMethodDefine weixinActionMethodDefine = new WeixinActionMethodDefine()
                .setHttpMethod(HttpMethod.GET)
                .setIsNeedAppid(false)
                .setUri("/cgi-bin/ticket/getticket")
                .putActionConfigParamter("type", "jsapi")
                .setWeixinBaseParamter(new WeixinBaseParamter().setToken(token));

        //获取jsticket的执行体
        String jsticket = null;
        try {
            jsticket = HttpUtils.request(weixinActionMethodDefine);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取到js-SDK的ticket并赋值保存
        String jsapiticket = pareJsonDate(jsticket, "ticket");
        log.info("jsapi_ticket================================================" + jsapiticket);
    }

    /**
     * 在返回的json字符串中获取想要的属性
     *
     * @param jsonData json字符串
     * @param param    想要获取的属性参数
     * @return 得到的参数
     */
    public String pareJsonDate(String jsonData, String param) {
        String params = JSONObject.parseObject(jsonData).getString(param);
        return params;
    }

    /**
     * 微信图文消息群发
     *
     * @param paramSendWeChat 微信消息群发所需要的参数
     * @return 返回发送的结果
     */
    public String sendGroupMessage(ParamSendWeChat paramSendWeChat) {

        //需要token,(appid,sercert)
        AccessToken tokengetTicket = this.getTokengetTicket(paramSendWeChat.getAppid(), paramSendWeChat.getAppSecret());
        String accessToken = tokengetTicket.getToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
        String jsonResult = null;
        try {
            //获取图片上传的id
            String mediaid = fileUpload.uploadImage(accessToken, paramSendWeChat.getFilePath());
            TeletextMessage teletextMessage = new TeletextMessage();
            Articles article = new Articles();
            article.setThumbmediaid(mediaid);
            article.setContent(paramSendWeChat.getContent());
            article.setTitle(paramSendWeChat.getTitle());
            article.setContentsourceurl(paramSendWeChat.getContentsourceurl());
            teletextMessage.setArticles(new Articles[]{article});
            //获取上传图文消息素材的id
            String textMessageId = fileUpload.uploadTextMessage(accessToken, teletextMessage);
            SendWeChat sendWeChat = new SendWeChat();
            sendWeChat.setTouser(paramSendWeChat.getTouser());
            sendWeChat.setMsgtype("mpnews");
            sendWeChat.setMpnews(new Mpnews().setMediaid(textMessageId));
            String jsonteletextMessage = JSONObject.toJSONString(sendWeChat);
            jsonResult = HttpUtils.sendPostJson(url, jsonteletextMessage);
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult = e.getMessage();
        }
        log.info("微信消息群发返回结果======================================" + jsonResult);
        return jsonResult;

    }

    /**
     * 微信支付统一下单接口
     *
     * @param sweepPay 请求生成微信二维码的url
     * @return 微信二维码的url
     */
    public String weixinpay(SweepPay sweepPay) {
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", sweepPay.getAppid());
        packageParams.put("mch_id", sweepPay.getMchid());
        // 随机字符串，长度要求在32位以内。推荐随机数生成算法
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        packageParams.put("nonce_str", strTime + strRandom);
        packageParams.put("body", sweepPay.getBody());
        packageParams.put("out_trade_no", sweepPay.getOuttradeno());  //商户订单号自定义只要保持唯一性即可
        packageParams.put("total_fee", sweepPay.getTotalfee()); //这个参数是以分为单位,账单中是以元为单位
        packageParams.put("spbill_create_ip", sweepPay.getSpbillcreateip());
        packageParams.put("notify_url", sweepPay.getNotifyurl()); //微信异步通知,填入下面的方法即可
        packageParams.put("trade_type", sweepPay.getTradetype());  //JSAPI(页面调用h5发起)，NATIVE(扫码支付,code_url返回一个二维码)
        packageParams.put("openid", sweepPay.getOpenid()); //trade_type为JSAPI时,必须传入
        try {
            packageParams.put("attach", URLEncoder.encode("SellerId=" + "某个变量", "GBK")); //加入此参数,后面异步接收时就可以原样获取到
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //签名 sign 第一次的签名
        //TODO 注意除了sign之外所有的参数必须放在创建签名中
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, sweepPay.getKey());
        packageParams.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        log.info("第一步:微信扫码支付发送请求的post数据为=========" + requestXML);
        String resXml = HttpUtils.sendXmlPost("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
        log.info("第二步:微信扫码支付返回数据为=========" + resXml);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String returncode = (String) map.get("return_code"); //此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
        String returnmsg = (String) map.get("return_msg"); //返回信息，如非空，为错误原因签名失败 参数格式校验错误
        log.info("返回通讯标识为=========" + returncode + "错误原因为=========" + returnmsg);
        if ("OK".equals(returnmsg)) {
            String errcodedes = (String) map.get("err_code_des");
            log.info("返回业务错误原因为=========" + errcodedes);
        }

        String urlCode = (String) map.get("code_url");
        log.info("第三步:微信扫码支付返回的url数据为=========" + urlCode);

        //如果是二维码扫码支付只需要获取到urlCode即可,以下可不需要
        SortedMap<Object, Object> payMap = new TreeMap<Object, Object>();
        String package1 = "prepay_id=" + map.get("prepay_id");
        payMap.put("appId", sweepPay.getAppid());
        String timeStamp = (new Date().getTime() / 1000) + "";
        payMap.put("timeStamp", timeStamp);
        payMap.put("nonceStr", strTime + strRandom);
        payMap.put("signType", "MD5");
        payMap.put("package", package1);
        // 第二次生成签名 ,以上这些参数需要传入到前端
        String paySign = PayCommonUtil.createSign("UTF-8", payMap, sweepPay.getKey());
        SweepH5Pay sweepH5Pay = new SweepH5Pay();
        sweepH5Pay.setAppId(sweepPay.getAppid());
        sweepH5Pay.setNonceStr(strTime + strRandom);
        sweepH5Pay.setPackage1(package1);
        sweepH5Pay.setTimeStamp(timeStamp);
        sweepH5Pay.setPaySign(paySign);
//        return sweepH5Pay; //如果h5发起支付返回这个实体类
        return urlCode;
    }

    /**
     * 微信异步通知,进行后端处理
     *
     * @param request  请求
     * @param response 相应
     */
    public void payNotifyUrl(HttpServletRequest request, HttpServletResponse response) {
        String inputLine;
        String result = ""; //获取到的xml信息
        Map<Object, Object> map = null;
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                result += inputLine;
            }
            map = XMLUtil.doXMLParse(result);
            //判断使否返回通讯成功的信息
            if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                //验证签名是否正确
                boolean b = PayCommonUtil.verifyWeixinNotify(map);
                if (b) {
                    //TODO 订单处理,例如将相关数据存入到数据库中
                    response.getWriter().write(PayCommonUtil.setXML("SUCCESS", "OK")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
                }
            }
            request.getReader().close();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 客服接口-发送消息
     *
     * @param appId 公众号的appid
     * @param appSecret  公众号的appsecret
     * @param cuatomerNews 消息实体类
     * @return 发送成功后的回调
     */
    public String customerSmsSend(String appId, String appSecret, CuatomerNews cuatomerNews) {
        AccessToken tokenByCode = allWeiXinRquest.getTokenByCode(appId, appSecret);
        String token = "";
        if (null != tokenByCode) {
            token = tokenByCode.getToken();
        }
        String toJSONString = JSONObject.toJSONString(cuatomerNews);
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
        String jsonResult = "";
        try {
            jsonResult = HttpUtils.sendPostJson(url, toJSONString);
            String errcode = JSONObject.parseObject(jsonResult).getString("errcode");
            String errmsg = JSONObject.parseObject(jsonResult).getString("errmsg");
            //如果错误码为空
            if (!"ok".equals(errmsg)) {
                log.debug("发送客服消息返回的数据为==" + jsonResult);
                return jsonResult;
            } else {
                return "success"; //说明发送成功
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }



    /**
     * 生成微信的临时二维码
     *
     * @param ticket 需要传入店铺编码或者联盟编码,以及需要传入的参数
     * @return ticket获取url即可
     */
    public Ticket getTicket(Ticket ticket) {
//        POST数据例子：{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
//        或者也可以使用以下POST数据创建字符串形式的二维码参数：{"expire_seconds": 604800, "action_name": "QR_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
        String shopCode = ticket.getShopCode();
        String lmcode = ticket.getLmcode();
        AccessToken tokengetTicket = allWeiXinRquest.getTokenByCode(shopCode, lmcode);
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + tokengetTicket.getToken();
        String json = "{\"expire_seconds\": 2592000, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + ticket.getSceneStr() + "\"}}}";
        try {
            String sendPostJson = HttpUtils.sendPostJson(url, json);
            String errcode = JSONObject.parseObject(sendPostJson).getString("errcode");
            if (!"".equals(errcode) && null != errcode && !"null".equals(errcode)) {
                String errmsg = JSONObject.parseObject(sendPostJson).getString("errmsg");
                log.debug("获取二维码错误异常errcode=" + errcode + ",errmsg=" + errmsg);
            } else {
                String ticket1 = JSONObject.parseObject(sendPostJson).getString("ticket");
                String expireSeconds = JSONObject.parseObject(sendPostJson).getString("expire_seconds");
                String url1 = JSONObject.parseObject(sendPostJson).getString("url");
                ticket.setTicket(ticket1);
                ticket.setUrl(url1);
                ticket.setExpireSeconds(expireSeconds);
                return ticket;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
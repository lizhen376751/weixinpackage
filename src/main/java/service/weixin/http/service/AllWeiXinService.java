package service.weixin.http.service;

import com.alibaba.fastjson.JSONObject;
import modules.third.message.module.CuatomerNews;
import modules.weixin.http.module.http.HttpMethod;
import modules.weixin.http.module.http.WeixinActionMethodDefine;
import modules.weixin.http.module.http.WeixinBaseParamter;
import modules.weixin.http.module.menu.Menu;
import modules.weixin.http.module.parammodule.*;
import modules.weixin.weixinmessage.*;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.weixin.http.accesstoken.service.AccessTokenService;
import service.weixin.http.util.PayCommonUtil;
import service.weixin.http.util.XMLUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


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
     * 微信环境url配置
     */
    @Autowired
    private URLConfig urlConfig;
    /**
     * token的service层
     */
    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 微信相关请求
     */
    @Autowired
    private AllWeiXinRquest allWeiXinRquest;

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
        String url = template.getUrl();
        if (null != url && !"".equals(url) && !"null".equals(url)) {
            int ahiTiXing = url.indexOf("ahiTiXing");
            if (ahiTiXing >= 0) {
                url = urlConfig.getWeixinURL() + url;
                log.info("ahi检车的报告详情url为======" + url);
                template.setUrl(url);
            }
        }
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
            WeixinActionMethodDefine weixinActionMethodDefine = new WeixinActionMethodDefine()
                    .setHttpMethod(HttpMethod.GET)
                    .setIsNeedAccssToken(false)
                    .setUri("/sns/oauth2/access_token")
                    .putActionConfigParamter("grant_type", "authorization_code")
                    .putActionConfigParamter("code", code)
                    .setWeixinBaseParamter(new WeixinBaseParamter().setAppid(appid).setSecret(appSecret));
            String request = HttpUtils.request(weixinActionMethodDefine);
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        //1.获取开发者的access_token
        AccessToken tokengetTicket = null;
        try {
            tokengetTicket = this.getTokengetTicket(appid, secret);
            String token = tokengetTicket.getToken();
            //2.获取openid
            OauthOpenIdToken oauthAccessToken = this.getOauthAccessToken(code, appid, secret);
            String openId = oauthAccessToken.getOpenId();
            WeixinActionMethodDefine weixinActionMethodDefine = new WeixinActionMethodDefine()
                    .setHttpMethod(HttpMethod.GET)
                    .setIsNeedAppid(false)
                    .setUri("/cgi-bin/user/info")
                    .putActionConfigParamter("lang", "zh_CN")
                    .putActionConfigParamter("openid", openId)
                    .setWeixinBaseParamter(new WeixinBaseParamter().setAppid(appid).setSecret(secret).setToken(token));
            String s = HttpUtils.request(weixinActionMethodDefine);
            //获取用户的昵称
            WeiXinUserInfo weiXinUserInfo = jsonToEntry(s);
            return weiXinUserInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
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
        packageParams.put("out_trade_no", sweepPay.getOuttradeno());
        packageParams.put("total_fee", sweepPay.getTotalfee());
        packageParams.put("spbill_create_ip", sweepPay.getSpbillcreateip());
        packageParams.put("notify_url", sweepPay.getNotifyurl());
        packageParams.put("trade_type", sweepPay.getTradetype());
        //签名 sign 通过签名算法计算得出的签名值，详见签名生成算法()
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
        return urlCode;
    }


    /**
     * 客服接口-发消息
     *
     * @param token 公众号的token
     * @param json  消息转换为json类型
     * @return 发送成功后的回调
     */
    public String customerSmsSend(String token, String json) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
        String jsonResult = "";
        try {
            jsonResult = HttpUtils.sendPostJson(url, json);
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
     * 客服发送图文消息(电子优惠券的详情url做了处理)
     *
     * @param cuatomerNews 图文消息
     * @return success或者错误原因
     */
    public String customerSendCard(String appId, String xAppSecret, CuatomerNews cuatomerNews) {
        AccessToken tokenByCode = allWeiXinRquest.getTokenByCode(appId, xAppSecret);
        String token = "";
        if (null != tokenByCode) {
            token = tokenByCode.getToken();
        }
        String toJSONString = JSONObject.toJSONString(cuatomerNews);
        String customerSmsSend = this.customerSmsSend(token, toJSONString);
        return customerSmsSend;
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
package com.lizhen.weixinpackage.modules.weixin.parammodule;

import java.io.Serializable;


/**
 * 微信扫码支付请求url(生成二维码)实体类
 * Created by lizhen on 2017/5/19.
 */
public class SweepPay implements Serializable {
//
//    1.公众账号ID appid 是 String (32) wxd678efh567hg6787 微信支付分配的公众账号ID（企业号corpid即为此appId）
//    2.商户号mch_id 是 String (32) 1230000109 微信支付分配的商户号
//    3.随机字符串 nonce_str 是  String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS  随机字符串，长度要求在32位以内。推荐随机数生成算法
//    4.签名 sign 是 String(32) C380BEC2BFD727A4B6845133519F3AD6  通过签名算法计算得出的签名值，详见签名生成算法
//    5.商品描述 body 是 String(128)  腾讯充值中心-QQ会员充值  商品简单描述，该字段请按照规范传递，具体请见参数规定
//    6.商户订单号 out_trade_no 是 String(32) 20150806125346 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
//    7.标价金额 total_fee 是 Int 88 订单总金额，单位为分，详见支付金额
//    8. 终端IP spbill_create_ip 是 String(16) 123.12.12.123 APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//    9.通知地址 notify_url 是 String(256) http://www.weixin.qq.com/wxpay/pay.php 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
//    10.交易类型 trade_type 是 String(16) JSAPI 取值如下：JSAPI，NATIVE，APP等，说明详见参数规定
//    11.商品ID product_id 否 String(32) 12235413214070356458058 trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
//    12.用户标识 openid 否 String(128) oUpF8uMuAJO_M2pxb1Q9zNjWeS6o trade_type=JSAPI时（即公众号支付），此参数必传
    /**
     * 微信的appid
     */
    private String appid;

    /**
     * 商户平台的商业号(商户号：开通微信支付后分配)
     */
    private String mchid;
    /**
     * 商户平台的key
     */
    private String key;

    /**
     * 价格 金额必须为整数  单位为分
     */
    private String totalfee;
    /**
     * 商品名称
     */
    private String body;
    /**
     * 订单号( 商户订单号：用户id + “|” + 随机16位字符)
     */
    private String outtradeno; // 订单号
    /**
     * 获取发起电脑 ip(本机的Ip)
     */
    private String spbillcreateip;
    /**
     * 回调接口(支付成功后，回调地址,只要进入该项目的微信支付异步通知的地址即可)
     */
    private String notifyurl;
    /**
     * 交易类型
     * trade_type="NATIVE"（即扫码支付）
     */
    private String tradetype;
    /**
     * trade_type=NATIVE时（即扫码支付），此参数必传。
     * 商户根据自己业务传递的参数 当trade_type=NATIVE时必填
     */
    private String productid;

    /**
     * 用户标识 openid trade_type=JSAPI时（即公众号支付），此参数必传
     */
    private String openid;


    /**
     * SweepPay(微信扫码支付请求url(生成二维码)实体类) 字符串形式
     *
     * @return SweepPay(微信扫码支付请求url(生成二维码)实体类)字符串
     */
    @Override
    public String toString() {
        return "SweepPay{" +
                "appid='" + appid + '\'' +
                ", mchid='" + mchid + '\'' +
                ", key='" + key + '\'' +
                ", totalfee='" + totalfee + '\'' +
                ", body='" + body + '\'' +
                ", outtradeno='" + outtradeno + '\'' +
                ", spbillcreateip='" + spbillcreateip + '\'' +
                ", notifyurl='" + notifyurl + '\'' +
                ", tradetype='" + tradetype + '\'' +
                ", productid='" + productid + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }

    /**
     * openid
     *
     * @return
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * openid
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }


    /**

     * 获取 微信的appid
     *
     * @return appid 微信的appid
     */
    public String getAppid() {
        return this.appid;
    }

    /**
     * 设置 微信的appid
     *
     * @param appid 微信的appid
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    /**
     * 获取 商户平台的商业号(商户号：开通微信支付后分配)
     *
     * @return mchid 商户平台的商业号(商户号：开通微信支付后分配)
     */
    public String getMchid() {
        return this.mchid;
    }

    /**
     * 设置 商户平台的商业号(商户号：开通微信支付后分配)
     *
     * @param mchid 商户平台的商业号(商户号：开通微信支付后分配)
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setMchid(String mchid) {
        this.mchid = mchid;
        return this;
    }

    /**
     * 获取 商户平台的key
     *
     * @return key 商户平台的key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 设置 商户平台的key
     *
     * @param key 商户平台的key
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 获取 价格 金额必须为整数  单位为分
     *
     * @return totalfee 价格 金额必须为整数  单位为分
     */
    public String getTotalfee() {
        return this.totalfee;
    }

    /**
     * 设置 价格 金额必须为整数  单位为分
     *
     * @param totalfee 价格 金额必须为整数  单位为分
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setTotalfee(String totalfee) {
        this.totalfee = totalfee;
        return this;
    }

    /**
     * 获取 商品名称
     *
     * @return body 商品名称
     */
    public String getBody() {
        return this.body;
    }

    /**
     * 设置 商品名称
     *
     * @param body 商品名称
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * 获取 订单号( 商户订单号：用户id + “|” + 随机16位字符)
     *
     * @return outtradeno 订单号( 商户订单号：用户id + “|” + 随机16位字符)
     */
    public String getOuttradeno() {
        return this.outtradeno;
    }

    /**
     * 设置 订单号( 商户订单号：用户id + “|” + 随机16位字符)
     *
     * @param outtradeno 订单号( 商户订单号：用户id + “|” + 随机16位字符)
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
        return this;
    }

    /**
     * 获取 获取发起电脑 ip(本机的Ip)
     *
     * @return spbillcreateip 获取发起电脑 ip(本机的Ip)
     */
    public String getSpbillcreateip() {
        return this.spbillcreateip;
    }

    /**
     * 设置 获取发起电脑 ip(本机的Ip)
     *
     * @param spbillcreateip 获取发起电脑 ip(本机的Ip)
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setSpbillcreateip(String spbillcreateip) {
        this.spbillcreateip = spbillcreateip;
        return this;
    }

    /**
     * 获取 回调接口(支付成功后，回调地址)
     *
     * @return notifyurl 回调接口(支付成功后，回调地址)
     */
    public String getNotifyurl() {
        return this.notifyurl;
    }

    /**
     * 设置 回调接口(支付成功后，回调地址)
     *
     * @param notifyurl 回调接口(支付成功后，回调地址)
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
        return this;
    }

    /**
     * 获取 交易类型      trade_type="NATIVE"（即扫码支付）
     *
     * @return tradetype 交易类型      trade_type="NATIVE"（即扫码支付）
     */
    public String getTradetype() {
        return this.tradetype;
    }

    /**
     * 设置 交易类型      trade_type="NATIVE"（即扫码支付）
     *
     * @param tradetype 交易类型      trade_type="NATIVE"（即扫码支付）
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setTradetype(String tradetype) {
        this.tradetype = tradetype;
        return this;
    }

    /**
     * 获取 trade_type=NATIVE时（即扫码支付），此参数必传。     商户根据自己业务传递的参数 当trade_type=NATIVE时必填
     *
     * @return productid trade_type=NATIVE时（即扫码支付），此参数必传。     商户根据自己业务传递的参数 当trade_type=NATIVE时必填
     */
    public String getProductid() {
        return this.productid;
    }

    /**
     * 设置 trade_type=NATIVE时（即扫码支付），此参数必传。     商户根据自己业务传递的参数 当trade_type=NATIVE时必填
     *
     * @param productid trade_type=NATIVE时（即扫码支付），此参数必传。     商户根据自己业务传递的参数 当trade_type=NATIVE时必填
     * @return 返回 SweepPay(微信扫码支付请求url(生成二维码)实体类)
     */
    public SweepPay setProductid(String productid) {
        this.productid = productid;
        return this;
    }
}

package com.lizhen.weixinpackage.modules.weixin.parammodule;

import java.io.Serializable;

/**
 * 查询企业付款所需要的参数实体类
 * Created by lizhen on 2017/11/13.
 */
public class Querytransfer implements Serializable{

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;
    /**
     * 商户订单号
     */
    private String partner_trade_no;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     *  商户号的appid
     */
    private String appid;
    /**
     * 商户秘钥key
     */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    @Override
    public String toString() {
        return "Querytransfer{" +
                "nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", partner_trade_no='" + partner_trade_no + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", appid='" + appid + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

package com.lizhen.weixinpackage.modules.weixin.parammodule;

import java.io.Serializable;

/**
 * 微信支付后的异步通知
 * Created by lizhen on 2017/11/17.
 */

public class NotifyWxpay implements Serializable{
    /**
     *  小程序ID
     */
    private String appid;
    /**
     *  商户号
     */
    private String mch_id;
    /**
     *  设备号
     */
    private String device_info;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     *  签名
     */
    private String sign;
    /**
     *  签名类型
     */
    private String sign_type;
    /**
     * 业务结果
     */
    private String result_code;
    /**
     * 错误代码
     */
    private String err_code;
    /**
     *  错误代码描述
     */
    private String err_code_des;
    /**
     *  用户标识
     */
    private String openid;
    /**
     * 是否关注公众账号
     */
    private String is_subscribe;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     *  付款银行
     */
    private String bank_type;
    /**
     * 订单金额
     */
    private String total_fee;
    /**
     * 应结订单金额
     */
    private String settlement_total_fee;
    /**
     * 货币种类
     */
    private String fee_type;
    /**
     * 现金支付金额
     */
    private String cash_fee;
    /**
     * 现金支付货币类型
     */
    private String cash_fee_type;
    /**
     *  微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商家数据包
     */
    private String attach;
    /**
     *  支付完成时间
     */
    private String time_end;

    @Override
    public String toString() {
        return "NotifyWxpay{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", openid='" + openid + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", bank_type='" + bank_type + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", settlement_total_fee='" + settlement_total_fee + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee='" + cash_fee + '\'' +
                ", cash_fee_type='" + cash_fee_type + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                '}';
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}

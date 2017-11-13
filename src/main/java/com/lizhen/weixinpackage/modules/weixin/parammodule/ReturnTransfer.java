package com.lizhen.weixinpackage.modules.weixin.parammodule;

import java.io.Serializable;

/**
 * 企业查询付款记录返回的参数
 * Created by lizhen on 2017/11/13.
 */
public class ReturnTransfer implements Serializable {

    /**
     * 商户单号 商户使用查询API填写的单号的原路返回
     */
    private String partner_trade_no;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 付款单号 调用企业付款API时，微信系统内部产生的单号
     */
    private String detail_id;
    /**
     * 转账状态
     * SUCCESS:转账成功
     * FAILED:转账失败
     * PROCESSING:处理中
     */
    private String status;
    /**
     * 失败原因
     */
    private String reason;
    /**
     * 收款用户openid
     */
    private String openid;
    /**
     * 收款用户姓名
     */
    private String transfer_name;
    /**
     * 付款金额
     */
    private Integer payment_amount;
    /**
     * 转账时间
     */
    private String transfer_time;
    /**
     * 付款描述
     */
    private String desc;

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

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTransfer_name() {
        return transfer_name;
    }

    public void setTransfer_name(String transfer_name) {
        this.transfer_name = transfer_name;
    }

    public Integer getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Integer payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getTransfer_time() {
        return transfer_time;
    }

    public void setTransfer_time(String transfer_time) {
        this.transfer_time = transfer_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ReturnTransfer{" +
                "partner_trade_no='" + partner_trade_no + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", detail_id='" + detail_id + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", openid='" + openid + '\'' +
                ", transfer_name='" + transfer_name + '\'' +
                ", payment_amount=" + payment_amount +
                ", transfer_time='" + transfer_time + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

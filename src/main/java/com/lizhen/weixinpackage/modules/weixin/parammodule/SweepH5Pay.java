package com.lizhen.weixinpackage.modules.weixin.parammodule;

/**
 * 微信浏览器内部h5页面发起支付申请所需要参数
 * Created by lizhen on 2017/10/24.
 */
public class SweepH5Pay {
//    公众号id appId 是 String(16) wx8888888888888888 商户注册具有支付权限的公众号成功后即可获得
//    时间戳 timeStamp 是 String(32) 1414561699 当前的时间，其他详见时间戳规则
//    随机字符串 nonceStr 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位。推荐随机数生成算法
//    订单详情扩展字符串 package 是 String(128) prepay_id=123456789 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
//    签名方式 signType 是 String(32) MD5 签名算法，暂支持MD5
//    签名 paySign 是 String(64) C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法
    /**
     * 公众号id
     */
    private String appId;
    /**
     * 时间戳 10位数字到秒即可
     */
    private String timeStamp;
    /**
     *  随机字符串 nonceStr
     */
    private String nonceStr;
    /**
     * TODO package是java私有字段,暂时换成package1
     *  订单详情扩展字符串  prepay_id=123456789
     *  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
     */
    private String package1;
    /**
     * 签名方式  MD5 签名算法，暂支持MD5
     */
    private String signType = "MD5";
    /**
     *  签名 将以上参数按照微信规则进行签名,得到的签名数据(签名有工具,可以用工具测试签名是否正确)
     */
    private String paySign;

    @Override
    public String toString() {
        return "SweepH5Pay{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", package1='" + package1 + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackage1() {
        return package1;
    }

    public void setPackage1(String package1) {
        this.package1 = package1;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}

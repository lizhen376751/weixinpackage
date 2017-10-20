package com.lizhen.weixinpackage.service.weixinservice.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 微信支付相关工具类
 * Created by lizhen on 2017/5/19.
 */
public final class PayCommonUtil {
    private PayCommonUtil() {
    }

    /**
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     *
     * @param characterEncoding 字符
     * @param packageParams     字符
     * @param aPIKEY            appid
     * @return 是否正确
     */
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String aPIKEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + aPIKEY);

        //算出摘要
        String mysign = MD5Util.md5Encode(sb.toString(), characterEncoding).toLowerCase();
        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();

        //System.out.println(tenpaySign + "    " + mysign);
        return tenpaySign.equals(mysign);
    }

    /**
     * 通过微信的签名算法计算得出的签名值，详见签名生成算法
     *
     * @param characterEncoding 编码格式
     * @param packageParams     参数列表
     * @param key               商户平台的key
     * @return 字符串
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Util.md5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }


    /**
     * @param parameters 请求参数
     * @return 将请求参数转换为xml格式的string
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 微信支付异步通知返给微信服务器
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    /**
     * 微信支付异步通知之后进行签名验证
     * @param map
     * @return
     */
    public static boolean verifyWeixinNotify(Map<Object, Object> map) {
        SortedMap<Object, Object> parameterMap = new TreeMap<>();
        String sign = (String) map.get("sign");
        for (Object keyValue : map.keySet()) {
            if (!keyValue.toString().equals("sign")) {
                parameterMap.put(keyValue.toString(), map.get(keyValue));
            }

        }
        String createSign = createSign("UTF-8", parameterMap, "商户秘钥的key");
        if (createSign.equals(sign)) {
            return true;
        } else {
            return false;
        }

    }


}

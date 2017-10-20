package com.lizhen.weixinpackage.controller.zhifubao;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by Administrator on 2017/10/17.
 */
public class ZhiFuBaoPayController {

    /**
     * 支付宝支付页面
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/aliPay")
    public void aliPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("支付宝支付页面");
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String orderNo = request.getParameter("orderNo");
        //订单名称，必填
        String subjectName = request.getParameter("subjectName");
        //付款金额，必填
        String total_fee = request.getParameter("fee");
        //商品描述，可空
        String body = "法海风控 " + subjectName;
        if ("money".equals(body)) {
            body = "法海风控  余额充值";
        }
        //把请求参数打包成map
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("out_trade_no", orderNo);
        sParaTemp.put("subject", subjectName);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("body", body);
        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");
        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println(sHtmlText);
        out.println(sHtmlText);

    }
}

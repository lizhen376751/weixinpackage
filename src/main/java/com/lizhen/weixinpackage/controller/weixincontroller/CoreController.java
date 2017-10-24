package com.dudu.weixin.control;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.weixin.util.Constant;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置微信的url点击提交之后,与服务器的交互
 * 后期微信所有的接受消息,都会进入这个路径
 * lizhen
 */
@Controller
public class CoreController extends HttpServlet {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(CoreController.class);
    /**
     * 引入消息处理接口
     */
    @Reference(timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;

    /**
     * @param request  请求
     * @param response 返回
     * @throws ServletException 异常
     * @throws IOException      io异常
     */
    @RequestMapping(value = "/urlconfig", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
         */
        String signature = request.getParameter("signature");
        /**
         *时间戳
         */
        String timestamp = request.getParameter("timestamp");
        /**
         *随机数
         */
        String nonce = request.getParameter("nonce");
        /**
         *随机字符串
         */
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        /**
         *通过检验signature对请求进行校验。若确认此次GET请求来自微信服务器，
         * 请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
         *
         */
        boolean b = apiAllWeiXiRequest.checkSignature(signature, timestamp, nonce, Constant.TOKEN);
        if (b) {
            out.print(echostr);
            log.info("配置验证=================================" + b);
        }
        out.close();
    }

    /**
     * post方法用于接收微信服务端消息,并进行回复消息
     *
     * @param out      返回消息
     * @param request  请求
     * @param response 回应请求
     */
    @RequestMapping(value = "/urlconfig", method = RequestMethod.POST)
    public void doPost(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        String lmcode = request.getParameter("lmcode");
        String shopcode = request.getParameter("shopcode");
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        map.put("lmcode", lmcode);
        map.put("shopcode", shopcode);
        log.info("店铺编码=====" + shopcode + "===联盟编码=====" + lmcode);
        log.info("接受消息为==========================" + map);
        String receivemessage = apiAllWeiXiRequest.receivemessage(map);
        log.info("发送消息为==========================" + receivemessage);
        out.println(receivemessage);
        if (map.get("Event").equals("SCAN")) {
            //如果通过二维码的事件关注之后,需要重新发送一条数据
            String receivemessage1 = apiAllWeiXiRequest.receivemessage(map);
            log.info("二维码关注事件发送消息为==========================" + receivemessage);
            if (!"".equals(receivemessage1) && !"null".equals(receivemessage1) && null != receivemessage1) {
                out.println(receivemessage);
            }
        }
        out.close();
        // 释放资源
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputStream = null;


    }
}

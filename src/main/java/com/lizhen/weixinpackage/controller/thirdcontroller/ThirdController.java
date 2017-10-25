package com.lizhen.weixinpackage.controller.thirdcontroller;


import com.alibaba.fastjson.JSONObject;
import com.lizhen.weixinpackage.controller.weixincontroller.AllWeiXinController;
import com.lizhen.weixinpackage.modules.third.authorizationinfo.module.AuthorizationInfo;
import com.lizhen.weixinpackage.modules.third.commonwx.module.AESParams;
import com.lizhen.weixinpackage.modules.third.commonwx.module.ComponentAccessToken;
import com.lizhen.weixinpackage.modules.third.commonwx.module.ComponentVerifyTicket;
import com.lizhen.weixinpackage.modules.third.commonwx.module.PreAuthCode;
import com.lizhen.weixinpackage.modules.third.message.module.CustomerText;
import com.lizhen.weixinpackage.modules.third.message.module.TextContent;
import com.lizhen.weixinpackage.service.thirdservice.aes.AesException;
import com.lizhen.weixinpackage.service.thirdservice.service.ThirdService;
import com.lizhen.weixinpackage.service.weixinservice.util.ThirdUtil;
import com.lizhen.weixinpackage.service.weixinservice.util.XMLUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 第三方开发平台
 * Created by lizhen on 2017/7/20.
 */
@Controller
public class ThirdController {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(ThirdController.class);
//====================================================================================================

    /**
     * 引入消息处理接口
     */
    @Autowired
    private AllWeiXinController apiAllWeiXiRequest;

    /**
     * 第三方开发接口
     */
    @Autowired
    private ThirdService apiThird;

    /**
     * 用于接收取消授权通知、授权成功通知、授权更新通知，也用于接收ticket
     * 每十分钟推送 授权事件接收url
     *
     * @param request  请求
     * @param response 相应
     * @throws IOException       异常
     * @throws AesException      异常
     * @throws DocumentException 异常
     */
    @RequestMapping(value = "/service/thirdservice/event/authorize")
    public void acceptAuthorizeEvent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AesException, DocumentException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        log.debug("微信推送Ticket消息10分钟一次-----------" + df.format(new Date()));
        //在第三方平台创建审核通过后，微信服务器会向其“授权事件接收URL”每隔10分钟定时推送component_verify_ticket。
        // 第三方平台方在收到ticket推送后也需进行解密（详细请见【消息加解密接入指引】），接收到后必须直接返回字符串success。
        processAuthorizeEvent(request);
        output(response, "success"); // 工具类：回复微信服务器"文本消息"
    }

    /**
     * 工具类：回复微信服务器"文本消息"
     *
     * @param response     相应
     * @param returnvaleue 相应信息的返回
     */
    public void output(HttpServletResponse response, String returnvaleue) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(returnvaleue);
            pw.flush();
            log.debug("回复微信服务器的消息为=============" + returnvaleue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理十分钟推送过来的授权事件的
     *
     * @param request 请求
     * @throws IOException       异常
     * @throws AesException      异常
     * @throws DocumentException 异常
     */
    public void processAuthorizeEvent(HttpServletRequest request) throws IOException, DocumentException, AesException {
        //Token微信开放平台上，服务方设置的接收消息的校验token
        String nonce = request.getParameter("nonce"); //URL上原有参数,随机数
        String timestamp = request.getParameter("timestamp"); //URL上原有参数,时间戳
        String signature = request.getParameter("signature");
        String msgSignature = request.getParameter("msg_signature"); //前文描述密文消息体
        log.debug("处理十分钟推送过来的授权事件的,nonce=" + nonce + ",timestamp=" + timestamp + ",signature=" + signature + ",msgSignature=" + msgSignature);
        if (msgSignature == null || "".equals(msgSignature) || "null".equals(msgSignature)) {
            log.debug("没有加密直接返回了.....");
            return; // 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
        }
        /**
         * 判断是否是授权的公众号发来的消息
         */
        boolean isValid = apiAllWeiXiRequest.checkSignature(signature, timestamp, nonce, ThirdUtil.TOKEN);
        log.debug("处理十分钟推送过来的授权事件的====是否加密" + isValid);
        if (isValid) {
            StringBuilder sb = new StringBuilder();
            BufferedReader in = request.getReader();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            String xml = sb.toString();
            if (xml.indexOf("AppId") != -1) {
                xml = xml.replace("AppId", "ToUserName");
            }
            log.debug("授权事件原始 Xml=====================\r\n" + xml);
            /**
             * 解密
             */
            AESParams aesParams = new AESParams();
            aesParams.setToken(ThirdUtil.TOKEN).setAppId(ThirdUtil.APPID).setEncodingAesKey(ThirdUtil.ENDCODINGAESKEY).
                    setMsgSignature(msgSignature).setNonce(nonce).setTimestamp(timestamp).setXml(xml);
            xml = apiThird.decrypt(aesParams);
            log.debug("授权事件解密后 Xml==========================\r\n" + xml);
            /**
             *  在解密后的xml中获取ticket 并保存Ticket
             */
            ComponentVerifyTicket componentVerifyTicket = apiThird.processAuthorizationEvent(xml);
            log.debug("在解密后的xml中获取ticket 并保存返回的实体类componentVerifyTicket=" + componentVerifyTicket.toString());
            if (null != componentVerifyTicket) {
                String componentVerifyTicket1 = componentVerifyTicket.getComponentVerifyTicket();
                if (null != componentVerifyTicket1 && !"".equals(componentVerifyTicket1) && !"null".equals(componentVerifyTicket1)) {
                    /**
                     * 保存token
                     */
                    ComponentAccessToken componentAccessToken = apiThird.getComponentAccessToken(componentVerifyTicket);
                    log.debug("保存第三方的开发平台的token=" + componentAccessToken.toString());
                    /**
                     * 保存预授权码
                     */
                    PreAuthCode preAuthCode = apiThird.getPreAuthCode(componentAccessToken);
                    log.debug("保存预授权码preAuthCode=" + preAuthCode.toString());
                }
            }

        }
    }


//==========授权及回调处理=========================================================================

    /**
     * 引导页 一键授权功能
     *
     * @param request  请求
     * @param response 相应
     * @throws IOException 网络异常
     */
    @RequestMapping(value = "/goAuthor", method = {RequestMethod.GET, RequestMethod.POST})
    public void goAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer requestUrl = request.getRequestURL();
        String tempContextUrl = requestUrl.delete(requestUrl.length() - request.getRequestURI().length(), requestUrl.length()).append("/").toString();
        //网页的一个按钮点击之后直接进行跳转至这个页面,然后客户进行授权
        ComponentAccessToken componentAccessToken = new ComponentAccessToken();
        componentAccessToken.setAppid(ThirdUtil.APPID);
        PreAuthCode preAuthCode = apiThird.getPreAuthCode(componentAccessToken);
        String redirectUri = tempContextUrl + "authorCallback"; //授权后的回调url
        log.debug("从request中获取的域名为=====" + tempContextUrl + ",预授权码=" + preAuthCode);
        String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + ThirdUtil.APPID + "&pre_auth_code=" + preAuthCode.getPreAuthCode() + "&redirect_uri=" + redirectUri;
        response.sendRedirect(url);
    }

    /**
     * 授权后的回调ur处理
     *
     * @param request  请求
     * @param response 相应
     */
    @RequestMapping(value = "/authorCallback")
    public void authorCallback(HttpServletRequest request, HttpServletResponse response) {
        String authCode = request.getParameter("auth_code"); //授权码
        String expiresIn = request.getParameter("expires_in"); //有效期
        log.debug("再回调url中获取授权码" + authCode + ",有效期=" + expiresIn);
        ComponentVerifyTicket componentVerifyTicket = new ComponentVerifyTicket();
        componentVerifyTicket.setAppId(ThirdUtil.APPID);
        //保存授权信息
        AuthorizationInfo authorizationInfo = apiThird.getAuthorizationInfo(componentVerifyTicket, authCode);
        log.debug("保存的授权信息=" + authorizationInfo.toString());
    }


//===========全网发布检测===================================================================================================


    /**
     * 事件以及文本消息接受url
     *
     * @param appid    公众号的appid
     * @param request  请求
     * @param response 相应
     * @throws IOException       网络异常
     * @throws AesException      加密或者解密异常
     * @throws DocumentException 解析xml
     */
    @RequestMapping(value = "/thirdMessage/{appid}/callback")
    public void acceptMessageAndEvent(HttpServletRequest request, HttpServletResponse response, @PathVariable String appid
    ) throws IOException, AesException, DocumentException {
        String msgSignature = request.getParameter("msg_signature");
        log.debug("第三方平台全网发布----事件以及文本消息接受---appid" + appid);
        if (msgSignature == null || "".equals(msgSignature) || "null".equals(msgSignature)) {
            log.debug("没有加密直接返回了.....");
            return; // 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        String xml = sb.toString();
        log.debug("第三方平台全网发布----事件以及文本消息接受原始xml====\r\n" + xml);
        checkWeixinAllNetworkCheck(request, response, xml);
    }


    /**
     * @param request  请求
     * @param response 相应
     * @param xml      发送过来的加密xml
     * @throws AesException      加解密异常
     * @throws DocumentException xml解析
     */
    public void checkWeixinAllNetworkCheck(HttpServletRequest request, HttpServletResponse response, String xml) throws AesException, DocumentException {
        Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String msgSignature = request.getParameter("msg_signature");
        /**
         * 解密
         */
        AESParams aesParams = new AESParams();
        aesParams.setToken(ThirdUtil.TOKEN).setAppId(ThirdUtil.APPID).setEncodingAesKey(ThirdUtil.ENDCODINGAESKEY).
                setMsgSignature(msgSignature).setNonce(nonce).setTimestamp(timestamp).setXml(xml);
        xml = apiThird.decrypt(aesParams);
        log.debug("第三方平台全网发布----事件以及文本消息解密后的xml====\r\n" + xml);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(xml);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.jdom.JDOMException e) {
            e.printStackTrace();
        }
        String receivemessage = apiAllWeiXiRequest.receivemessage(map);
        Document doc = DocumentHelper.parseText(receivemessage);
        Element rootElt = doc.getRootElement();
        String msgType = rootElt.elementText("MsgType");
        String toUserName = rootElt.elementText("ToUserName");
        String content = rootElt.elementText("Content");

        log.debug("消息分发返回结果" + receivemessage);
        if (content.indexOf("_from_api") != -1) {
            log.debug("直接返回空字符串");
            output(response, "");
            log.debug("返回空字符串接着发送客服消息");
            CustomerText customerText = new CustomerText();
            customerText.setTouser(toUserName);
            customerText.setMsgtype(msgType);
            TextContent textContent = new TextContent();
            textContent.setContent(content);
            customerText.setText(textContent);
            /**
             * 获取授权公众号的token
             */
            ComponentVerifyTicket componentVerifyTicket = new ComponentVerifyTicket();
            componentVerifyTicket.setAppId(ThirdUtil.APPID);
            AuthorizationInfo authorizationInfo = apiThird.getAuthorizationInfo(componentVerifyTicket, content.substring(0, content.length() - 9));
            log.debug("获取授权公众号的授权信息" + authorizationInfo.toString());
            //发送客服消息
            String json = JSONObject.toJSONString(customerText);
            //TODO 注意此时开放平台与公众号获取token的方式不同,相应的方法需要重新写
            String kefuxiaoxi = apiAllWeiXiRequest.customerSmsSend("appid","appsecret",json);
            log.debug("发送客服消息" + kefuxiaoxi);
        } else {
            /**
             * 加密
             */
            AESParams aesParams1 = new AESParams();
            aesParams1.setToken(ThirdUtil.TOKEN).setAppId(ThirdUtil.APPID).setEncodingAesKey(ThirdUtil.ENDCODINGAESKEY).
                    setMsgSignature(msgSignature).setNonce(nonce).setTimestamp(timestamp).setXml(receivemessage);
            receivemessage = apiThird.encrypt(aesParams1);
            log.debug("加密后的xml的文件为===\r\n" + receivemessage);
            output(response, receivemessage);
        }


    }
}

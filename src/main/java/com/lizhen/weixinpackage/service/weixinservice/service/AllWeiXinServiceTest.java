package com.lizhen.weixinpackage.service.weixinservice.service;

import com.alibaba.fastjson.JSONObject;
import com.lizhen.weixinpackage.modules.third.message.module.CuatomerArticlessss;
import com.lizhen.weixinpackage.modules.third.message.module.CuatomerNews;
import com.lizhen.weixinpackage.modules.third.message.module.NewsArticles;
import com.lizhen.weixinpackage.modules.weixin.menu.Button;
import com.lizhen.weixinpackage.modules.weixin.menu.CommonButton;
import com.lizhen.weixinpackage.modules.weixin.menu.ComplexButton;
import com.lizhen.weixinpackage.modules.weixin.menu.Menu;
import com.lizhen.weixinpackage.modules.weixin.parammodule.*;
import com.lizhen.weixinpackage.modules.weixin.weixinmessage.ParamSendWeChat;
import com.lizhen.weixinpackage.modules.weixin.weixinmessage.Template;
import com.lizhen.weixinpackage.modules.weixin.weixinmessage.TemplateData;
import com.lizhen.weixinpackage.service.weixinservice.util.WeiXinParam;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24.
 */
public class AllWeiXinServiceTest {


    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(AllWeiXinServiceTest.class);
    @Autowired
    private AllWeiXinService allWeiXinService;

    //获取开发者的token以及js的ticket
    @org.junit.Test
    public void getTokengetTicket() throws Exception {
        try {
            AccessToken tokengetTicket = allWeiXinService.getTokengetTicket("wxd2e4688648e2ae13", "e372b3f7050982e8c45c7223d830f9b1");
            log.info("获取开发者的token==========================" + tokengetTicket.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //配置验证
    @org.junit.Test
    public void checkSignature() throws Exception {
        boolean b = allWeiXinService.checkSignature("9265761c4e1154041eba12fbbc3c6d01d05a79d4", "1493285904", "1454245089", "duduchewang");
        log.info("url验证为=====================================" + b);
    }

    //生成菜单
    @org.junit.Test
    public void createMenu() throws Exception {
        //        http://lizhen12.tunnel.2bdata.com
        String COMMONURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd4e76e01e4a6e3b7&redirect_uri=http://lizhen12.tunnel.2bdata.com/oauthLoginServlet?lmcode=CS000";

        CommonButton btn11 = new CommonButton();
        btn11.setName("联盟卡包");
        btn11.setType("view");
        btn11.setUrl(COMMONURL + "_lmkInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("AHI指数");
        btn12.setType("view");
        btn12.setUrl(COMMONURL + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn13 = new CommonButton();
        btn13.setName("车辆保养");
        btn13.setType("view");
        btn13.setUrl(COMMONURL + "_baoYangList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn14 = new CommonButton();
        btn14.setName("消费记录");
        btn14.setType("view");
        btn14.setUrl(COMMONURL + "_xiaoFeiList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn15 = new CommonButton();
        btn15.setName("个人中心");
        btn15.setType("view");
        btn15.setUrl(COMMONURL + "_personalCenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn21 = new CommonButton();
        btn21.setName("联盟介绍");
        btn21.setType("view");
        btn21.setUrl(COMMONURL + "_lianMengJieShao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn22 = new CommonButton();
        btn22.setName("服务导航");
        btn22.setType("view");
        btn22.setUrl(COMMONURL + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn23 = new CommonButton();
        btn23.setName("我的预约");
        btn23.setType("view");
        btn23.setUrl(COMMONURL + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn24 = new CommonButton();
        btn24.setName("车险投保");
        btn24.setType("view");
        btn24.setUrl(COMMONURL + "_cheXianTouBao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn31 = new CommonButton();
        btn31.setName("养车百科");
        btn31.setType("view");
        btn31.setUrl(COMMONURL + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("优惠促销");
        btn32.setType("view");
        btn32.setUrl(COMMONURL + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn33 = new CommonButton();
        btn33.setName("车友会");
        btn33.setType("view");
        btn33.setUrl("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?shopcode=CS000&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4");

        CommonButton btn25 = new CommonButton();
        btn25.setName("快商通");
        btn25.setType("view");
        btn25.setUrl("http://kefu6.kuaishang.cn/bs/im.htm?cas=56463___619761&fi=58696&from=9");

        CommonButton btn35 = new CommonButton();
        btn35.setName("服务导航");
        btn35.setType("view");
        btn35.setUrl("http://4ec43db5.ngrok.io/fuwudaohang");

        CommonButton btn34 = new CommonButton();
        btn34.setName("退出账号");
        btn34.setType("view");
        btn34.setUrl(COMMONURL + "_logout" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn33, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("活动123");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn34});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        String menu1 = allWeiXinService.createMenu(menu, "wxd4e76e01e4a6e3b7", "dd1e044b9208d43a5a31238e5ee053c7");
        log.info("个人创建菜单======================================" + menu1);

    }

    //模板消息的发送
    @org.junit.Test
    public void sendTemplateMsg() throws Exception {
        Template template = new Template();
        template.setTopColor("#00DD00");
        template.setToUser("owQtWt8L6RVxj_cTUaPyH27RWdbA");
        template.setUrl("http://eqxiu.com/s/aX0KOheU");
        template.setTemplateId("PR8mojxujaqOYtdp3LJa_Rt8N0QxwRdtG6IOJ71r0js");
        List<TemplateData> paras = new ArrayList<TemplateData>();
        paras.add(new TemplateData().setName("first").setColor("#FF3333").setValue("我们已收到您的货款，开始为您打包商品，请耐心等待: "));
        paras.add(new TemplateData().setName("keyword1").setColor("#0044BB").setValue("¥20.00"));
        paras.add(new TemplateData().setName("keyword2").setColor("#0044BB").setValue("火烧牛干巴"));
        paras.add(new TemplateData().setName("keyword3").setColor("#0044BB").setValue("火烧牛干巴"));
        paras.add(new TemplateData().setName("keyword4").setColor("#0044BB").setValue("火烧牛干巴"));
        paras.add(new TemplateData().setName("keyword5").setColor("#0044BB").setValue("火烧牛干巴"));
        paras.add(new TemplateData().setName("remark").setColor("#AAAAAA").setValue("感谢你对我们商城的支持!!!!"));
        template.setTemplateParamList(paras);
        String b = allWeiXinService.sendTemplateMsg("wxf0af72edbe855d28", "fa12f20abeabc7c8ca3ebe777ceb2229", template);
        log.info("模板消息发送=============================" + b);
    }

    //获取openid以及网页授权的token
    @org.junit.Test
    public void getOauthAccessToken() throws Exception {
        OauthOpenIdToken oauthAccessToken = allWeiXinService.getOauthAccessToken("001LcZpP1dJQp41QB7qP1rh8qP1LcZpg", "wx465f646a62e6b22d", "237416cc575a0313d5d01c00a9d9eb53");
        log.info("获取网页授权以及openid==========================" + oauthAccessToken.toString());
    }

    //获取用户的基本信息
    @org.junit.Test
    public void getWeiXinUserInfo() throws Exception {
        WeiXinUserInfo weiXinUserInfo = allWeiXinService.getWeiXinUserInfo("041yWHoa1Y6PdT1QMura1caioa1yWHoS", "wx91ee3b29615c49c7", "2e2a94909cf9fca29cccc111bf7896f5");
        log.info("用户的别名为:==========================" + weiXinUserInfo);
    }

    //根据openid获取微信用户的基本信息
    @org.junit.Test
    public void getWeiXinUserInfoByOpenid() throws Exception {
        WeiXinUserInfo ss = allWeiXinService.getWeiXinUserInfoByOpenid("0533001", "", "oSsYXwMun4NrZE8b_OQi6kMaPyg4");
        log.debug("======"+ss.toString());
    }

    //进入消息处理 被动的回复消息
    @org.junit.Test
    public String receivemessage(Map<String, String> map) {
        map = new HashMap<String, String>();
        map.put("ToUserName", "gh_3c884a361561");
        map.put("FromUserName", "ozy4qt5QUADNXORxCVipKMV9dss0");
        map.put("CreateTime", "1502242343");
        map.put("MsgType", "text");
        map.put("Content", "QUERY_AUTH_CODE:queryauthcode@@@TtNOi8GZN94hkYtgA9BTa0nvltJwL1W6UqSBnzXdRjdQ-7lplFJTp5Y1up9QSmMSSrxxNFG-kalalYWeXgoplw");
        map.put("MsgId", "6452081734258176800");
        String message = MsgDispatcher.processMessage(map);
        return message;
    }


    //微信消息群发
    @org.junit.Test
    public void sendGroupMessage() throws Exception {
        ParamSendWeChat paramSendWeChat = new ParamSendWeChat();
        paramSendWeChat.setAppid("wxd4e76e01e4a6e3b7");
        paramSendWeChat.setAppSecret("dd1e044b9208d43a5a31238e5ee053c7");
        paramSendWeChat.setTitle("测试群发消息");
        paramSendWeChat.setContent("李振测试微信消息群发");
        paramSendWeChat.setFilePath("C:/Users/Public/Pictures/Sample Pictures/meiche.jpg");
        paramSendWeChat.setTouser(new String[]{"oSsYXwMun4NrZE8b_OQi6kMaPyg4", "oSsYXwNfsML7Qs5gwcdCpC549l-E"});
        paramSendWeChat.setContentsourceurl("http://wx.pdu.duduchewang.cn");
        String message = allWeiXinService.sendGroupMessage(paramSendWeChat);
        log.info("微信消息群发返回测试结果为:========================================" + message);
    }

    //获取微信扫码支付url或者返回h5页面发起支付参数
    @org.junit.Test
    public void weixinpay() throws Exception {
        SweepPay sweepPay = new SweepPay();
        sweepPay.setAppid("wxac57fd22a1194510");
        sweepPay.setBody("北京经典汽车服务有限公司");
        sweepPay.setKey("fa0eebc9727aebba6860c34935150834");
        sweepPay.setMchid("1229927502");
        sweepPay.setNotifyurl("http://shop.duduchewang.com/userServlet/Notify");
        sweepPay.setOuttradeno("XC20170519023");
        sweepPay.setProductid("100001");
        sweepPay.setSpbillcreateip("127.0.0.1");
        sweepPay.setTotalfee("45000");
        sweepPay.setTradetype("NATIVE");
        sweepPay.setProductid("454545454545");
        String weixinpay = allWeiXinService.weixinpay(sweepPay);
        log.info("微信扫码支付url为:========================================" + weixinpay);
    }

    @org.junit.Test
    public void payNotifyUrl() throws Exception {

    }
    @Test
    public void transfers() throws Exception {
//        <xml><amount>21016500</amount>
//        <check_name>NO_CHECK</check_name>
//        <desc>微信公众号提现</desc>
//        <mch_appid>wx8aba18d2d947c70f</mch_appid>
//        <mchid>1490678512</mchid>
//        <nonce_str>1444049807</nonce_str>
//        <openid>oF0xCwsEnNJT7AQTnnycx3By0rVA</openid>
//        <partner_trade_no>1510641844451</partner_trade_no>
//        <sign><![CDATA[978FFE8DF6BE46236E82C02E610AEAB1]]></sign>
//        <spbill_create_ip>127.0.0.1</spbill_create_ip>
//        </xml>
    }

    @Test
    public void querytransfers() throws Exception {

    }
    //客服接口-发消息
    @org.junit.Test
    public void customerSmsSend() throws Exception {
        AccessToken tokengetTicket = allWeiXinService.getTokengetTicket("wxd4e76e01e4a6e3b7", "dd1e044b9208d43a5a31238e5ee053c7");
        CuatomerNews cuatomerNews = new CuatomerNews();
        cuatomerNews.setTouser("oSsYXwMun4NrZE8b_OQi6kMaPyg4");
        cuatomerNews.setMsgtype("news");
        CuatomerArticlessss customerNewsMessage = new CuatomerArticlessss();
        List list = new ArrayList<NewsArticles>();
        NewsArticles newsArticles = new NewsArticles();
        newsArticles.setDescription("我愛你們!!!!")
                .setPicurl("http://image.duduchewang.cn/0533001/bill/XS20170821033/work/shigong/4778/158/1503480646583.jpg")
                .setTitle("客服圖文消息")
                .setUrl("https://zhidao.baidu.com/question/361355540136762972.html");
        list.add(0, newsArticles);
        customerNewsMessage.setArticles(list);
        cuatomerNews.setNews(customerNewsMessage);
        String toJSONString = JSONObject.toJSONString(cuatomerNews);
        String customerSmsSend = allWeiXinService.customerSmsSend(WeiXinParam.APPID,WeiXinParam.APPSecret,toJSONString);
        log.debug("客服發送圖文消息發送后返回結果=====" + customerSmsSend);
    }

    //获取微信临时二维码(含参数)
    @org.junit.Test
    public void getTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setShopCode("0533001").setSceneStr("测试二维码");
        Ticket ticket1 = allWeiXinService.getTicket(ticket);
        log.debug("获取临时二维码ticket1=" + ticket1.toString());
    }

}
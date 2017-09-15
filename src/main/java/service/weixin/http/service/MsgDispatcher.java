package service.weixin.http.service;


import modules.weixin.weixinmessage.Article;
import modules.weixin.weixinmessage.NewsMessage;
import modules.weixin.weixinmessage.TextMessage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 被动回复消息业务处理分发器
 * Created by lizhen on 2017/4/23.
 */

public final class MsgDispatcher {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(MsgDispatcher.class);

    private MsgDispatcher() {
    }

    /**
     *
     *
     * @param map 接受到的消息
     * @return 返回不同类型的xml消息
     */
    /**
     * 根据对XML的解析后获取相应的类型,进行不同的业务处理
     *
     * @param map 接受到的消息
     * @return 返回不同类型的xml消息
     */
    public static String processMessage(Map<String, String> map) {


        /**
         *用户openid
         */
        String openid = map.get("FromUserName");
        /**
         *公众号原始ID
         */
        String mpid = map.get("ToUserName");
        /***
         * 店铺编码
         */
        String shopcode = map.get("shopcode");
        /**
         * 联盟code
         */
        String lmcode = map.get("lmcode");
        //全网发布检测相关测试
        log.debug("公众号原始ID=" + mpid + "是否相等=" + mpid.equals("gh_3c884a361561"));

        if (mpid.equals("gh_3c884a361561")) {
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime() / 1000);
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            //事件检测
            if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 关注事件及推送地理位置
                //普通文本消息
                txtmsg.setContent(map.get("Event") + "from_callback");
                log.info("==============模拟粉丝触发专用测试公众号的事件！");
                return MessageUtil.textMessageToXml(txtmsg);
            }
            //文本消息检测
            if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //自动检测第二步
                log.debug("自动检测第二步content的值=" + map.get("Content") + "相等=" + "TESTCOMPONENT_MSG_TYPE_TEXT".equals(map.get("Content")));
                log.debug("自动检测第二步content的值=" + map.get("Content") + "相等=" + StringUtils.startsWithIgnoreCase(map.get("Content"), "QUERY_AUTH_CODE"));
                if ("TESTCOMPONENT_MSG_TYPE_TEXT".equals(map.get("Content"))) {
                    txtmsg.setContent("TESTCOMPONENT_MSG_TYPE_TEXT_callback");
                    return MessageUtil.textMessageToXml(txtmsg);
                } else if (StringUtils.startsWithIgnoreCase(map.get("Content"), "QUERY_AUTH_CODE")) {
                    //自动检测第三步 传送xml时,如果是第三方开发平台的,第一次回复空,第二次根据xml的判断直接调用客服接口
                    String content = map.get("Content");
                    String split = content.split(":")[1].toString() + "_from_api";
                    txtmsg.setContent(split);
                    return MessageUtil.textMessageToXml(txtmsg);
                }
            }
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime() / 1000);
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent("感谢您的关注!");
            log.info("==============这是文本消息！");
            return MessageUtil.textMessageToXml(txtmsg);

        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 关注事件及推送地理位置
            //对图文消息
            NewsMessage newmsg = new NewsMessage();
            newmsg.setToUserName(openid);
            newmsg.setFromUserName(mpid);
            newmsg.setCreateTime(new Date().getTime() / 1000);
            newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            Article article = new Article();

//            http://www.duduchewang.com/images/banweixin.jpg  图片展示
//            http://eqxiu.com/s/aX0KOheU  图片详情跳转url
//            "http://shop.duduchewang.com/upload/"+ strWxShopcode + "/shopimg/" + WelcomeImg;  图片地址
            if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //微信关注事件
                article.setDescription("点击图片查看详情"); //图文消息的描述
                article.setPicUrl("http://image.duduchewang.cn/0533001/baoxian/D21A0247BAE310E9/1/1496903333065.jpg"); //图文消息图片地址
                article.setTitle("操作手册");  //图文消息标题
                article.setUrl("http://image.duduchewang.cn/0533001/baoxian/D21A0247BAE310E9/1/1496903333065.jpg");  //图文url链接
                List<Article> list = new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                log.info("==============联盟微信关注事件！");
                return MessageUtil.newsMessageToXml(newmsg);
            }

            //地理推送事件
            if (map.get("Event").equals("LOCATION")) {
                log.info("==============地理推送事件！");
                return "地理位置获取";
            }
            //通过二维码关注事件
            if (map.get("Event").equals("SCAN")) {
                log.info("==============通过二维码关注事件！");
                //TODO 通过openid判断是否已经领取,如果没有领取则发送消息
                article.setDescription("点击图片查看详情"); //图文消息的描述
                article.setPicUrl("http://www.duduchewang.com/images/banweixin.jpg"); //图文消息图片地址
                article.setTitle("操作手册");  //图文消息标题
                article.setUrl("http://image.duduchewang.cn/0533001/baoxian/D21A0247BAE310E9/1/1496903333065.jpg");  //图文url链接
                List<Article> list = new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageToXml(newmsg);
            }
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            log.info("==============这是图片消息！");
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            log.info("==============这是链接消息！");
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            log.info("==============这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            log.info("==============这是语音消息！");
        }

        return null;
    }
}

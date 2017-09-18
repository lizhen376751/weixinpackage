package service.weixinservice.service;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import modules.weixin.weixinmessage.Article;
import modules.weixin.weixinmessage.NewsMessage;
import modules.weixin.weixinmessage.TextMessage;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 * Created by lizhen on 2017/4/23.
 */
public final class MessageUtil {
    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(MessageUtil.class);
    /**
     * 将对象封装成xml的处理
     */

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                private boolean cdata = true;

                //对部分字段不加标记
                public void startNode(String name, Class clazz) {
                    //修改标记的开关
                    if (name.equals("createTime") || name.equals("articleCount")) {
                        cdata = false;
                    } else {
                        cdata = true;
                    }
                    //将首字母改为大写
                    if (name.equals("xml") || name.equals("item")) {
                        log.info(null);
                    } else {
                        if (StringUtils.isNotBlank(name)) {
                            name = name.substring(0, 1).toUpperCase()
                                    + name.substring(1);
                        }
                    }
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    private MessageUtil() {
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param document 从request中获取输入流
     * @return Map<String, String>解析后的xml
     */
    public static Map<String, String> parseXml(Document document) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
//        InputStream inputStream = request.getInputStream();
        // 读取输入流
//        SAXReader reader = new SAXReader();
//        Document document = null;
//        try {
//            document = reader.read(inputStream);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
//        // 释放资源
//        try {
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        inputStream = null;

        return map;
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息
     * @return 文本消息的xml类型
     */
    public static String textMessageToXml(TextMessage textMessage) {

        xstream.alias("xml", textMessage.getClass());
        log.info(xstream.toXML(textMessage));
        return xstream.toXML(textMessage);
    }

    /**
     * @param newsMessage 多图文消息的实体类
     * @return 字符串
     * @Description: 图文消息对象转换成xml
     * @author lizhen
     * @date 2017年6月8日 下午4:14:09
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

}
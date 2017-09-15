package modules.weixin.weixinmessage;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * The type Text message.
 *
 * @Description: 文本消息消息体  Created by lizhen on 2017/4/23.
 */
public class TextMessage extends BaseMessage implements Serializable {

    /**
     * 回复的消息内容
     */
//    @XStreamAlias("Content")
    @XmlElement(name = "Content")
    private String content;


    /**
     * TextMessage(@Description: 文本消息消息体) 字符串形式
     *
     * @return TextMessage(@Description: 文本消息消息体)字符串
     */
    @Override
    public String toString() {
        return "content:" + content;
    }

    /**
     * 获取 回复的消息内容
     *
     * @return content 回复的消息内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 回复的消息内容
     *
     * @param content 回复的消息内容
     * @return 返回 TextMessage(@Description: 文本消息消息体)
     */
    public TextMessage setContent(String content) {
        this.content = content;
        return this;
    }
}
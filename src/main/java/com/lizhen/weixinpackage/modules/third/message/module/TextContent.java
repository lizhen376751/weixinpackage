package modules.third.message.module;

import java.io.Serializable;

/**
 * 文本消息的内容字段
 * Created by lizhen on 2017/7/24.
 */

public class TextContent implements Serializable {
    /**
     * 内容
     */
    private String content;

    /**
     *  TextContent(文本消息的内容字段) 字符串形式
     * @return TextContent(文本消息的内容字段)字符串
     */
    @Override
    public String toString() {
        return "content:" + content;
    }

    /**
     * 获取 内容
     * @return content 内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 内容
     * @param content 内容
     * @return 返回 TextContent(文本消息的内容字段)
     */
    public TextContent setContent(String content) {
        this.content = content;
        return this;
    }
}

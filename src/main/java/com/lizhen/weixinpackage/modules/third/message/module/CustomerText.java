package modules.third.message.module;


import java.io.Serializable;

/**
 * 客服消息的文本接口
 * Created by lizhen on 2017/7/24.
 */

public class CustomerText extends CustomerBaseMessage implements Serializable {
    /**
     * 文本内容
     */
    private TextContent text;


    /**
     *  CustomerText(客服消息的文本接口) 字符串形式
     * @return CustomerText(客服消息的文本接口)字符串
     */
    @Override
    public String toString() {
        return "text:" + text;
    }

    /**
     * 获取 文本内容
     * @return text 文本内容
     */
    public TextContent getText() {
        return this.text;
    }

    /**
     * 设置 文本内容
     * @param text 文本内容
     * @return 返回 CustomerText(客服消息的文本接口)
     */
    public CustomerText setText(TextContent text) {
        this.text = text;
        return this;
    }
}

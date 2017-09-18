package modules.weixin.weixinmessage;

import java.io.Serializable;

/**
 * 模板消息由于模板选取不同需要传入参数的名称、值、参数个数也不同
 * Created by lizhen on 2017/4/24.
 */
public class TemplateData implements Serializable {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数值
     */
    private String value;
    /**
     * 颜色
     */
    private String color;


    /**
     * TemplateData(Created by Administrator on 2017424.) 字符串形式
     *
     * @return TemplateData(Created by Administrator on 2017424.)字符串
     */
    @Override
    public String toString() {
        return "name:" + name + ",value:" + value + ",color:" + color;
    }

    /**
     * 获取 参数名称
     *
     * @return name 参数名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 参数名称
     *
     * @param name 参数名称
     * @return 返回 TemplateData(Created by Administrator on 2017424.)
     */
    public TemplateData setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取 参数值
     *
     * @return value 参数值
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 设置 参数值
     *
     * @param value 参数值
     * @return 返回 TemplateData(Created by Administrator on 2017424.)
     */
    public TemplateData setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 获取 颜色
     *
     * @return color 颜色
     */
    public String getColor() {
        return this.color;
    }

    /**
     * 设置 颜色
     *
     * @param color 颜色
     * @return 返回 TemplateData(Created by Administrator on 2017424.)
     */
    public TemplateData setColor(String color) {
        this.color = color;
        return this;
    }
}

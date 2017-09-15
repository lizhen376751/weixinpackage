package modules.weixin.menu;

import java.io.Serializable;

/**
 * 按钮类
 */
public class Button implements Serializable {
    /**
     * 名字
     */
    private String name;


    /**
     *  Button(按钮类) 字符串形式
     * @return Button(按钮类)字符串
     */
    @Override
    public String toString() {
        return "name:" + name;
    }

    /**
     * 获取 名字
     *
     * @return name 名字
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名字
     *
     * @param name 名字
     * @return 返回 Button(按钮类)
     */
    public Button setName(String name) {
        this.name = name;
        return this;
    }
}
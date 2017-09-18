package com.lizhen.weixinpackage.modules.weixin.menu;


/**
 * 菜单类
 * 二级按钮
 */
public class CommonButton extends Button {
    /**
     * 类型
     */
    private String type;
    /**
     * 按钮
     */
    private String key;
    /**
     * 路径
     */
    private String url;

    /**
     * Gets url.
     *
     * @return url url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets type.
     *
     * @return type type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets key.
     *
     * @return key key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key key
     */
    public void setKey(String key) {
        this.key = key;
    }
}
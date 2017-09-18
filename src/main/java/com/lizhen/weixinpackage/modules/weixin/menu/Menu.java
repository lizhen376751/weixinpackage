package com.lizhen.weixinpackage.modules.weixin.menu;


import java.io.Serializable;

/**
 * 菜单类
 */
public class Menu implements Serializable {
    /**
     * 按钮
     */
    private Button[] button;

    /**
     * Get button button [ ].
     *
     * @return 按钮 button [ ]
     */
    public Button[] getButton() {
        return button;
    }

    /**
     * Sets button.
     *
     * @param button 按钮
     */
    public void setButton(Button[] button) {
        this.button = button;
    }
}
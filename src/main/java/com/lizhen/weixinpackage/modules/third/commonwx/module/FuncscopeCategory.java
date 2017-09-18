package com.lizhen.weixinpackage.modules.third.commonwx.module;

import java.io.Serializable;

/**
 * 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
 * Created by lizhen on 2017/7/21.
 */

public class FuncscopeCategory implements Serializable {
    /**
     * ID为1到15时分别代表
     */
    private String id;

    /**
     * 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
     消息管理权限
     用户管理权限
     帐号服务权限
     网页服务权限
     微信小店权限
     微信多客服权限
     群发与通知权限
     微信卡券权限
     微信扫一扫权限
     微信连WIFI权限
     素材管理权限
     微信摇周边权限
     微信门店权限
     微信支付权限
     自定义菜单权限
     */

    /**
     * FuncscopeCategory(公众号授权给开发者的权限集列表，ID为1到15时分别代表：) 字符串形式
     *
     * @return FuncscopeCategory(公众号授权给开发者的权限集列表，ID为1到15时分别代表：)字符串
     */
    @Override
    public String toString() {
        return "id:" + id;
    }

    /**
     * 获取 ID为1到15时分别代表
     *
     * @return id ID为1到15时分别代表
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置 ID为1到15时分别代表
     *
     * @param id ID为1到15时分别代表
     * @return 返回 FuncscopeCategory(公众号授权给开发者的权限集列表，ID为1到15时分别代表：)
     */
    public FuncscopeCategory setId(String id) {
        this.id = id;
        return this;
    }
}

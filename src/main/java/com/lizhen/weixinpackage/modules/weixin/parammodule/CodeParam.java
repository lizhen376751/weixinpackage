package com.lizhen.weixinpackage.modules.weixin.parammodule;

import java.io.Serializable;

/**
 * 微信相关传参的封装
 * Created by lizhen on 2017/8/24.
 */

public class CodeParam implements Serializable {
    /**
     * 店铺编码
     */
    private String shopCode;
    /**
     * 联盟编码
     */
    private String lmcode;
    /**
     * 业务逻辑
     */
    private String bussiness;

    /**
     * CodeParam(微信相关传参的封装) 字符串形式
     *
     * @return CodeParam(微信相关传参的封装)字符串
     */
    @Override
    public String toString() {
        return "shopCode:" + shopCode + ",lmcode:" + lmcode + ",bussiness:" + bussiness;
    }

    /**
     * 获取 店铺编码
     *
     * @return shopCode 店铺编码
     */
    public String getShopCode() {
        return this.shopCode;
    }

    /**
     * 设置 店铺编码
     *
     * @param shopCode 店铺编码
     * @return 返回 CodeParam(微信相关传参的封装)
     */
    public CodeParam setShopCode(String shopCode) {
        this.shopCode = shopCode;
        return this;
    }

    /**
     * 获取 联盟编码
     *
     * @return lmcode 联盟编码
     */
    public String getLmcode() {
        return this.lmcode;
    }

    /**
     * 设置 联盟编码
     *
     * @param lmcode 联盟编码
     * @return 返回 CodeParam(微信相关传参的封装)
     */
    public CodeParam setLmcode(String lmcode) {
        this.lmcode = lmcode;
        return this;
    }

    /**
     * 获取 业务逻辑
     *
     * @return bussiness 业务逻辑
     */
    public String getBussiness() {
        return this.bussiness;
    }

    /**
     * 设置 业务逻辑
     *
     * @param bussiness 业务逻辑
     * @return 返回 CodeParam(微信相关传参的封装)
     */
    public CodeParam setBussiness(String bussiness) {
        this.bussiness = bussiness;
        return this;
    }
}

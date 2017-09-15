package modules.weixin.http.module.parammodule;

import java.io.Serializable;

/**
 * 微信生成二维码事件
 * Created by lizhen on 2017/8/23.
 */

public class Ticket implements Serializable {
    /**
     * 联盟编码
     */
    private String lmcode;
    /**
     * 店铺编码
     */
    private String shopCode;
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private String expireSeconds;
    /**
     * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值
     */
    private String actionName;
    /**
     * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    private String sceneId;
    /**
     * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    private String sceneStr;
    /**
     * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    private String ticket;

    /**
     * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    private String url;

    /**
     *  Ticket(微信生成二维码事件) 字符串形式
     * @return Ticket(微信生成二维码事件)字符串
     */
    @Override
    public String toString() {
        return "lmcode:" + lmcode + ",shopCode:" + shopCode + ",expireSeconds:" + expireSeconds + ",actionName:" + actionName + ",sceneId:" + sceneId
                + ",sceneStr:" + sceneStr + ",ticket:" + ticket + ",url:" + url;
    }

    /**
     * 获取 联盟编码
     * @return lmcode 联盟编码
     */
    public String getLmcode() {
        return this.lmcode;
    }

    /**
     * 设置 联盟编码
     * @param lmcode 联盟编码
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setLmcode(String lmcode) {
        this.lmcode = lmcode;
        return this;
    }

    /**
     * 获取 店铺编码
     * @return shopCode 店铺编码
     */
    public String getShopCode() {
        return this.shopCode;
    }

    /**
     * 设置 店铺编码
     * @param shopCode 店铺编码
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setShopCode(String shopCode) {
        this.shopCode = shopCode;
        return this;
    }

    /**
     * 获取 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @return expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    public String getExpireSeconds() {
        return this.expireSeconds;
    }

    /**
     * 设置 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setExpireSeconds(String expireSeconds) {
        this.expireSeconds = expireSeconds;
        return this;
    }

    /**
     * 获取 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值
     * @return actionName 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值
     */
    public String getActionName() {
        return this.actionName;
    }

    /**
     * 设置 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值
     * @param actionName 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setActionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    /**
     * 获取 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    public String getSceneId() {
        return this.sceneId;
    }

    /**
     * 设置 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setSceneId(String sceneId) {
        this.sceneId = sceneId;
        return this;
    }

    /**
     * 获取 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    public String getSceneStr() {
        return this.sceneStr;
    }

    /**
     * 设置 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
        return this;
    }

    /**
     * 获取 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * @return ticket 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    public String getTicket() {
        return this.ticket;
    }

    /**
     * 设置 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * @param ticket 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    /**
     * 获取 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     * @return url 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     * @param url 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     * @return 返回 Ticket(微信生成二维码事件)
     */
    public Ticket setUrl(String url) {
        this.url = url;
        return this;
    }
}

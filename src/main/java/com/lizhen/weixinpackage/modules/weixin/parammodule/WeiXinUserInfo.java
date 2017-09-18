package modules.weixin.parammodule;

import java.io.Serializable;

/**
 * 微信用户信息的实体类
 * Created by lizhen on 2017/4/22.
 */
public class WeiXinUserInfo implements Serializable {
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private String subscribe;
    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;
    /**
     * 用户的昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。
     */
    private String headimgurl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private String subscribetime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     */
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;
    /**
     * 用户所在的分组ID
     */
    private String groupid;

    /**
     *  WeiXinUserInfo(微信用户信息的实体类) 字符串形式
     * @return WeiXinUserInfo(微信用户信息的实体类)字符串
     */
    @Override
    public String toString() {
        return "subscribe:" + subscribe + ",openid:" + openid + ",nickname:" + nickname + ",sex:" + sex + ",city:" + city + ",country:" + country
                + ",province:" + province + ",language:" + language + ",headimgurl:" + headimgurl + ",subscribetime:" + subscribetime + ",unionid:" + unionid
                + ",remark:" + remark + ",groupid:" + groupid;
    }

    /**
     * 获取 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     *
     * @return subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    public String getSubscribe() {
        return this.subscribe;
    }

    /**
     * 设置 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     *
     * @param subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setSubscribe(String subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    /**
     * 获取 用户的标识，对当前公众号唯一
     *
     * @return openid 用户的标识，对当前公众号唯一
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * 设置 用户的标识，对当前公众号唯一
     *
     * @param openid 用户的标识，对当前公众号唯一
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    /**
     * 获取 用户的昵称
     *
     * @return nickname 用户的昵称
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * 设置 用户的昵称
     *
     * @param nickname 用户的昵称
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    /**
     * 获取 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @return sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * 设置 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @param sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }

    /**
     * 获取 用户所在城市
     *
     * @return city 用户所在城市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 设置 用户所在城市
     *
     * @param city 用户所在城市
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * 获取 用户所在国家
     *
     * @return country 用户所在国家
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * 设置 用户所在国家
     *
     * @param country 用户所在国家
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * 获取 用户所在省份
     *
     * @return province 用户所在省份
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置 用户所在省份
     *
     * @param province 用户所在省份
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    /**
     * 获取 用户的语言，简体中文为zh_CN
     *
     * @return language 用户的语言，简体中文为zh_CN
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * 设置 用户的语言，简体中文为zh_CN
     *
     * @param language 用户的语言，简体中文为zh_CN
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * 获取 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640640正方形头像），用户没有头像时该项为空。
     *
     * @return headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640640正方形头像），用户没有头像时该项为空。
     */
    public String getHeadimgurl() {
        return this.headimgurl;
    }

    /**
     * 设置 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640640正方形头像），用户没有头像时该项为空。
     *
     * @param headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640640正方形头像），用户没有头像时该项为空。
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
        return this;
    }

    /**
     * 获取 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     *
     * @return subscribetime 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    public String getSubscribetime() {
        return this.subscribetime;
    }

    /**
     * 设置 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     *
     * @param subscribetime 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setSubscribetime(String subscribetime) {
        this.subscribetime = subscribetime;
        return this;
    }

    /**
     * 获取 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     *
     * @return unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     */
    public String getUnionid() {
        return this.unionid;
    }

    /**
     * 设置 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     *
     * @param unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setUnionid(String unionid) {
        this.unionid = unionid;
        return this;
    }

    /**
     * 获取 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     *
     * @return remark 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     *
     * @param remark 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 获取 用户所在的分组ID
     *
     * @return groupid 用户所在的分组ID
     */
    public String getGroupid() {
        return this.groupid;
    }

    /**
     * 设置 用户所在的分组ID
     *
     * @param groupid 用户所在的分组ID
     * @return 返回 WeiXinUserInfo(微信用户信息的实体类)
     */
    public WeiXinUserInfo setGroupid(String groupid) {
        this.groupid = groupid;
        return this;
    }
}

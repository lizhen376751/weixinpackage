package modules.weixin.parammodule;

import java.io.Serializable;

/**
 * 通过code换取网页授权access_token及用户的openid
 */
public class OauthOpenIdToken implements Serializable {
    /**
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    private String accessToken;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private int expiresIn;
    /**
     * 用户刷新access_token
     */
    private String refreshToken;
    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    private String openId;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
    /**
     * 别名暂时用不到
     */
    private String nickname;




    /**
     * OauthOpenIdToken(通过code换取网页授权access_token及用户的openid) 字符串形式
     *
     * @return OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)字符串
     */
    @Override
    public String toString() {
        return "accessToken:" + accessToken + ",expiresIn:" + expiresIn + ",refreshToken:" + refreshToken + ",openId:" + openId + ",scope:" + scope
                + ",nickname:" + nickname;
    }

    /**
     * 获取 网页授权接口调用凭证注意：此access_token与基础支持的access_token不同
     *
     * @return accessToken 网页授权接口调用凭证注意：此access_token与基础支持的access_token不同
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * 设置 网页授权接口调用凭证注意：此access_token与基础支持的access_token不同
     *
     * @param accessToken 网页授权接口调用凭证注意：此access_token与基础支持的access_token不同
     * @return 返回 OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)
     */
    public OauthOpenIdToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * 获取 access_token接口调用凭证超时时间，单位（秒）
     *
     * @return expiresIn access_token接口调用凭证超时时间，单位（秒）
     */
    public int getExpiresIn() {
        return this.expiresIn;
    }

    /**
     * 设置 access_token接口调用凭证超时时间，单位（秒）
     *
     * @param expiresIn access_token接口调用凭证超时时间，单位（秒）
     * @return 返回 OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)
     */
    public OauthOpenIdToken setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    /**
     * 获取 用户刷新access_token
     *
     * @return refreshToken 用户刷新access_token
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * 设置 用户刷新access_token
     *
     * @param refreshToken 用户刷新access_token
     * @return 返回 OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)
     */
    public OauthOpenIdToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    /**
     * 获取 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     *
     * @return openId 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 设置 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     *
     * @param openId 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * @return 返回 OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)
     */
    public OauthOpenIdToken setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 获取 用户授权的作用域，使用逗号（）分隔
     *
     * @return scope 用户授权的作用域，使用逗号（）分隔
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * 设置 用户授权的作用域，使用逗号（）分隔
     *
     * @param scope 用户授权的作用域，使用逗号（）分隔
     * @return 返回 OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)
     */
    public OauthOpenIdToken setScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * 获取 别名暂时用不到
     *
     * @return nickname 别名暂时用不到
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * 设置 别名暂时用不到
     *
     * @param nickname 别名暂时用不到
     * @return 返回 OauthOpenIdToken(通过code换取网页授权access_token及用户的openid)
     */
    public OauthOpenIdToken setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}

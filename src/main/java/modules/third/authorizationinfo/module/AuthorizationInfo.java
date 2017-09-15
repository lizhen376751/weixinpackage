package modules.third.authorizationinfo.module;

import java.io.Serializable;

/**
 * 公众号凭据和授权信息
 * Created by lizhen on 2017/7/21.
 */

public class AuthorizationInfo implements Serializable {
    /**
     * 主键id
     */
    private int id;
    /**
     * 授权方appid
     */
    private String authorizerAppid;
    /**
     * 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     */
    private String authorizerAccessToken;
    /**
     * 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     */
    private String expiresIn;
    /**
     * 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），
     * 刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。
     */
    private String authorizerRefreshToken;
    /**
     * 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
     */
    private String funcInfo;
    /**
     * 授权时间
     */
    private Long authorizationInfoTime;


    /**
     *  AuthorizationInfo(公众号凭据和授权信息) 字符串形式
     * @return AuthorizationInfo(公众号凭据和授权信息)字符串
     */
    @Override
    public String toString() {
        return "id:" + id + ",authorizerAppid:" + authorizerAppid + ",authorizerAccessToken:" + authorizerAccessToken + ",expiresIn:" + expiresIn
                + ",authorizerRefreshToken:" + authorizerRefreshToken + ",funcInfo:" + funcInfo + ",authorizationInfoTime:" + authorizationInfoTime;
    }

    /**
     * 获取 主键id
     * @return id 主键id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 主键id
     * @param id 主键id
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 授权方appid
     * @return authorizerAppid 授权方appid
     */
    public String getAuthorizerAppid() {
        return this.authorizerAppid;
    }

    /**
     * 设置 授权方appid
     * @param authorizerAppid 授权方appid
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid;
        return this;
    }

    /**
     * 获取 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     * @return authorizerAccessToken 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     */
    public String getAuthorizerAccessToken() {
        return this.authorizerAccessToken;
    }

    /**
     * 设置 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     * @param authorizerAccessToken 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setAuthorizerAccessToken(String authorizerAccessToken) {
        this.authorizerAccessToken = authorizerAccessToken;
        return this;
    }

    /**
     * 获取 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     * @return expiresIn 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     */
    public String getExpiresIn() {
        return this.expiresIn;
    }

    /**
     * 设置 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     * @param expiresIn 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    /**
     * 获取 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），      刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。
     * @return authorizerRefreshToken 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），      刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。
     */
    public String getAuthorizerRefreshToken() {
        return this.authorizerRefreshToken;
    }

    /**
     * 设置 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），      刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。
     * @param authorizerRefreshToken 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），      刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setAuthorizerRefreshToken(String authorizerRefreshToken) {
        this.authorizerRefreshToken = authorizerRefreshToken;
        return this;
    }

    /**
     * 获取 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
     * @return funcInfo 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
     */
    public String getFuncInfo() {
        return this.funcInfo;
    }

    /**
     * 设置 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
     * @param funcInfo 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo;
        return this;
    }

    /**
     * 获取 授权时间
     * @return authorizationInfoTime 授权时间
     */
    public Long getAuthorizationInfoTime() {
        return this.authorizationInfoTime;
    }

    /**
     * 设置 授权时间
     * @param authorizationInfoTime 授权时间
     * @return 返回 AuthorizationInfo(公众号凭据和授权信息)
     */
    public AuthorizationInfo setAuthorizationInfoTime(Long authorizationInfoTime) {
        this.authorizationInfoTime = authorizationInfoTime;
        return this;
    }
}

package service.weixin.http.accesstoken.mapper;


import modules.weixin.http.module.parammodule.AccessToken;

/**
 * 微信的接口dao层
 * Created by lizhen on 2017/3/30.
 */

public interface AccessTokenDao {
    /**
     * 获取微信的token
     *
     * @param accessToken 微信的token
     * @return token信息
     */
    AccessToken getAccessToken(AccessToken accessToken);

    /**
     * 新增微信的token
     *
     * @param accessToken 微信的token
     * @return 主键id
     */
    Integer addAccessToken(AccessToken accessToken);

    /**
     * 修改微信token
     *
     * @param accessToken 微信的token
     * @return 主键id
     */
    Integer updateAccessToken(AccessToken accessToken);

    /**
     * 微信的token
     *
     * @param accessToken 微信token
     * @return 主键id
     */
    Integer deletAccessToken(AccessToken accessToken);


}

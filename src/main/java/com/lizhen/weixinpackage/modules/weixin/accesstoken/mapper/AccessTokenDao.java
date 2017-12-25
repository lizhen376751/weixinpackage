package com.lizhen.weixinpackage.modules.weixin.accesstoken.mapper;


import com.lizhen.weixinpackage.modules.weixin.parammodule.AccessToken;
import org.springframework.stereotype.Service;

/**
 * 微信的接口dao层
 * Created by lizhen on 2017/3/30.
 */

@Service
public class AccessTokenDao {
    /**
     * 获取微信的token
     *
     * @param accessToken 微信的token
     * @return token信息
     */
    public AccessToken getAccessToken(AccessToken accessToken){
        return accessToken;
    }

    /**
     * 新增微信的token
     *
     * @param accessToken 微信的token
     * @return 主键id
     */
    public Integer addAccessToken(AccessToken accessToken){
        return 1;
    }

    /**
     * 修改微信token
     *
     * @param accessToken 微信的token
     * @return 主键id
     */
    public Integer updateAccessToken(AccessToken accessToken){
        return 1;
    }

    /**
     * 微信的token
     *
     * @param accessToken 微信token
     * @return 主键id
     */
    public Integer deletAccessToken(AccessToken accessToken){
        return 1;
    }


}

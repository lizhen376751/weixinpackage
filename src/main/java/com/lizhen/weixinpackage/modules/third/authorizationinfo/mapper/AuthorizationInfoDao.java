package com.lizhen.weixinpackage.modules.third.authorizationinfo.mapper;


import com.lizhen.weixinpackage.modules.third.authorizationinfo.module.AuthorizationInfo;
import org.springframework.stereotype.Service;

/**
 * 授权信息的dao层
 * Created by lizhen on 2017/3/30.
 */
@Service
public class AuthorizationInfoDao {
    /**
     * 获取公众号的授权信息
     *
     * @param authorizationInfo 微信公众号的授权信息
     * @return 返回获取的授权信息
     */
    public AuthorizationInfo getAuthorizationInfo(AuthorizationInfo authorizationInfo){
        return authorizationInfo;
    }

    /**
     * 修改公众号的授权信息
     *
     * @param authorizationInfo 微信公众号的授权信息
     * @return 授权信息的id
     */
    public Integer updateAuthorizationInfo(AuthorizationInfo authorizationInfo){
        return 1;
    }

    /**
     * 新增授权信息
     *
     * @param authorizationInfo 公众号的授权信息
     * @return 主键id
     */
    public Integer addAuthorizationInfo(AuthorizationInfo authorizationInfo){
        return 1;
    }

    /**
     * 删除授权信息
     *
     * @param authorizationInfo 删除授权信息
     * @return 主键
     */
    public Integer deletAuthorizationInfo(AuthorizationInfo authorizationInfo){
        return 1;
    }


}

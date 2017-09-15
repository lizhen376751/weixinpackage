package third.authorizationinfo.mapper;


import modules.third.authorizationinfo.module.AuthorizationInfo;

/**
 * 授权信息的dao层
 * Created by lizhen on 2017/3/30.
 */

public interface AuthorizationInfoDao {
    /**
     * 获取公众号的授权信息
     *
     * @param authorizationInfo 微信公众号的授权信息
     * @return 返回获取的授权信息
     */
    AuthorizationInfo getAuthorizationInfo(AuthorizationInfo authorizationInfo);

    /**
     * 修改公众号的授权信息
     *
     * @param authorizationInfo 微信公众号的授权信息
     * @return 授权信息的id
     */
    Integer updateAuthorizationInfo(AuthorizationInfo authorizationInfo);

    /**
     * 新增授权信息
     *
     * @param authorizationInfo 公众号的授权信息
     * @return 主键id
     */
    Integer addAuthorizationInfo(AuthorizationInfo authorizationInfo);

    /**
     * 删除授权信息
     *
     * @param authorizationInfo 删除授权信息
     * @return 主键
     */
    Integer deletAuthorizationInfo(AuthorizationInfo authorizationInfo);


}

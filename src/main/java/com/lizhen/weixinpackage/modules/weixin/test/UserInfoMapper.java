package com.lizhen.weixinpackage.modules.weixin.test;

import java.util.List;

/**
 * Created by lizhen on 2017/9/19.
 */
public interface  UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectAll();
}

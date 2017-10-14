package com.lizhen.weixinpackage.modules.weixin.test;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
public interface  UserService {
    UserInfo getUserById(int id);

    List<UserInfo> getUsers();

    int insert(UserInfo userInfo);
}

package com.lizhen.weixinpackage.modules.weixin.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserById(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> getUsers() {
        return userInfoMapper.selectAll();
    }

    @Override
    public int insert(UserInfo userInfo) {

        int result = userInfoMapper.insert(userInfo);

        System.out.println(result);
        return result;
    }

}

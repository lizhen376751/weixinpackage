package com.lizhen.weixinpackage.modules.weixin.test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhen on 2017/9/19.
 */
@Service
public class  UserInfoMapper {
    public int deleteByPrimaryKey(Integer id){
        return 1;
    }

    public int insert(UserInfo record){
        return 1;
    }

    public int insertSelective(UserInfo record){
        return  1;
    }

    public UserInfo selectByPrimaryKey(Integer id){
        return new UserInfo();
    }

    public int updateByPrimaryKeySelective(UserInfo record){
        return 1;
    }

    public int updateByPrimaryKey(UserInfo record){
        return 1;
    }

    public List<UserInfo> selectAll(){
        return new ArrayList<UserInfo>();
    }

}

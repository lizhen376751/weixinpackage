package com.lizhen.weixinpackage.modules.weixin.test;

/**
 * Created by Administrator on 2017/9/19.
 */
public class UserInfo {
    private Integer id;

    private String uname;

    private Integer unumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getUnumber() {
        return unumber;
    }

    public void setUnumber(Integer unumber) {
        this.unumber = unumber;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", unumber=" + unumber +
                '}';
    }
}

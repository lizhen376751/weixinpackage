package com.lizhen.weixinpackage.modules.third.commonwx.module;

import java.io.Serializable;

/**
 * 公众号授权给开发者的权限集列表，ID为1到15时分别代表
 * Created by lizhen on 2017/8/9.
 */

public class FuncInfo implements Serializable {
    /**
     * 公众号授权给开发者的权限集列表
     */
    private FuncscopeCategory funcscopeCategory;


    /**
     * FuncInfo(公众号授权给开发者的权限集列表，ID为1到15时分别代表) 字符串形式
     *
     * @return FuncInfo(公众号授权给开发者的权限集列表，ID为1到15时分别代表)字符串
     */
    @Override
    public String toString() {
        return "funcscopeCategory:" + funcscopeCategory;
    }

    /**
     * 获取 公众号授权给开发者的权限集列表
     *
     * @return funcscopeCategory 公众号授权给开发者的权限集列表
     */
    public FuncscopeCategory getFuncscopeCategory() {
        return this.funcscopeCategory;
    }

    /**
     * 设置 公众号授权给开发者的权限集列表
     *
     * @param funcscopeCategory 公众号授权给开发者的权限集列表
     * @return 返回 FuncInfo(公众号授权给开发者的权限集列表，ID为1到15时分别代表)
     */
    public FuncInfo setFuncscopeCategory(FuncscopeCategory funcscopeCategory) {
        this.funcscopeCategory = funcscopeCategory;
        return this;
    }
}

package com.lizhen.weixinpackage.modules.weixin.http;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface WeixinExceptionHandler {
    /**
     * 异常处理
     *
     * @param e 异常栈
     */
    void handle(Exception e);
}

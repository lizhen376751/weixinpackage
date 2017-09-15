package modules.weixin.http;

import java.io.Serializable;

/**
 * 发送get或者post请求
 * Created by lizhen on 2017/4/13.
 */
public enum HttpMethod implements Serializable {
    /**
     * get
     */
    GET,
    /**
     * post
     */
    POST;


}
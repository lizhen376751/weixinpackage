package modules.weixin.http;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信方法定义
 * Created by Administrator on 2017/4/13.
 */
public class WeixinActionMethodDefine implements Serializable {
//    https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKE
    /**
     * 联盟code或者店铺code
     */
    private String code;
    /**
     * HTTP方法(get或者post)
     */
    private HttpMethod httpMethod;
    /**
     * 请求参数格式(xml或者json)
     */
    private ParamterContentType paramterContentType;

    /**
     * 是否必须AccessToken(默认为true)
     */
    private boolean isNeedAccssToken = true;
    /**
     * 是否需要appid和seret
     */
    private boolean isNeedAppid = true;
    /**
     * 资源路径
     */
    private String uri; //类似于: /cgi-bin/template/api_set_industry
    /**
     * 基础参数
     */
    private WeixinBaseParamter weixinBaseParamter; //类似于appid之类的

    /**
     * 接口基础配置参数
     */
    private Map<String, String> actionConfigParamter = new HashMap<String, String>(); //grant_type=client_credential


    /**
     * 接口所需业务参数
     */
    private List<String> requestBusinessParamters = new ArrayList<String>(); //post请求的参数
    /**
     * 默认异常处理器
     */
    private WeixinExceptionHandler exceptionHandler;
    /**
     * 接口post的参数
     */
    private Map<String, String> actionPostParamter = new HashMap<String, String>();


    /**
     * WeixinActionMethodDefine(微信方法定义) 字符串形式
     *
     * @return WeixinActionMethodDefine(微信方法定义)字符串
     */
    @Override
    public String toString() {
        return "code:" + code + ",httpMethod:" + httpMethod + ",paramterContentType:" + paramterContentType + ",isNeedAccssToken:" + isNeedAccssToken
                + ",isNeedAppid:" + isNeedAppid + ",uri:" + uri + ",weixinBaseParamter:" + weixinBaseParamter + ",actionConfigParamter:" + actionConfigParamter
                + ",requestBusinessParamters:" + requestBusinessParamters + ",exceptionHandler:" + exceptionHandler + ",actionPostParamter:" + actionPostParamter;
    }

    /**
     * 获取 联盟code或者店铺code
     *
     * @return code 联盟code或者店铺code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置 联盟code或者店铺code
     *
     * @param code 联盟code或者店铺code
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 获取 HTTP方法(get或者post)
     *
     * @return httpMethod HTTP方法(get或者post)
     */
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    /**
     * 设置 HTTP方法(get或者post)
     *
     * @param httpMethod HTTP方法(get或者post)
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    /**
     * 获取 请求参数格式(xml或者json)
     *
     * @return paramterContentType 请求参数格式(xml或者json)
     */
    public ParamterContentType getParamterContentType() {
        return this.paramterContentType;
    }

    /**
     * 设置 请求参数格式(xml或者json)
     *
     * @param paramterContentType 请求参数格式(xml或者json)
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setParamterContentType(ParamterContentType paramterContentType) {
        this.paramterContentType = paramterContentType;
        return this;
    }

    /**
     * 获取 是否必须AccessToken(默认为true)
     *
     * @return isNeedAccssToken 是否必须AccessToken(默认为true)
     */
    public boolean isIsNeedAccssToken() {
        return this.isNeedAccssToken;
    }

    /**
     * 设置 是否必须AccessToken(默认为true)
     *
     * @param isNeedAccssToken 是否必须AccessToken(默认为true)
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setIsNeedAccssToken(boolean isNeedAccssToken) {
        this.isNeedAccssToken = isNeedAccssToken;
        return this;
    }

    /**
     * 获取 是否需要appid和seret
     *
     * @return isNeedAppid 是否需要appid和seret
     */
    public boolean isIsNeedAppid() {
        return this.isNeedAppid;
    }

    /**
     * 设置 是否需要appid和seret
     *
     * @param isNeedAppid 是否需要appid和seret
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setIsNeedAppid(boolean isNeedAppid) {
        this.isNeedAppid = isNeedAppid;
        return this;
    }

    /**
     * 获取 资源路径
     *
     * @return uri 资源路径
     */
    public String getUri() {
        return this.uri;
    }

    /**
     * 设置 资源路径
     *
     * @param uri 资源路径
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setUri(String uri) {
        this.uri = uri;
        return this;
    }

    /**
     * 获取 基础参数
     *
     * @return weixinBaseParamter 基础参数
     */
    public WeixinBaseParamter getWeixinBaseParamter() {
        return this.weixinBaseParamter;
    }

    /**
     * 设置 基础参数
     *
     * @param weixinBaseParamter 基础参数
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setWeixinBaseParamter(WeixinBaseParamter weixinBaseParamter) {
        this.weixinBaseParamter = weixinBaseParamter;
        return this;
    }

    /**
     * 新增设置 接口基础配置参数
     *
     * @param key   接口基础配置参数 元素 key
     * @param value 接口基础配置参数 元素 value
     * @return WeixinActionMethodDefine(微信方法定义) weixin action method define
     */
    public WeixinActionMethodDefine putActionConfigParamter(String key, String value) {
        this.actionConfigParamter.put(key, value);
        return this;
    }

    /**
     * 批量新增设置 接口基础配置参数
     *
     * @param actionConfigParamter 接口基础配置参数 元素集合
     * @return WeixinActionMethodDefine(微信方法定义) weixin action method define
     */
    public WeixinActionMethodDefine putAllActionConfigParamter(Map<String, String> actionConfigParamter) {
        this.actionConfigParamter.putAll(actionConfigParamter);
        return this;
    }

    /**
     * 获取 接口基础配置参数
     *
     * @return actionConfigParamter 接口基础配置参数
     */
    public Map<String, String> getActionConfigParamter() {
        return this.actionConfigParamter;
    }

    /**
     * 设置 接口基础配置参数
     *
     * @param actionConfigParamter 接口基础配置参数
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setActionConfigParamter(Map<String, String> actionConfigParamter) {
        this.actionConfigParamter = actionConfigParamter;
        return this;
    }

    /**
     * 新增 接口所需业务参数
     *
     * @param item 接口所需业务参数 元素
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine addRequestBusinessParamters(String item) {
        this.requestBusinessParamters.add(item);
        return this;
    }

    /**
     * 批量新增 接口所需业务参数
     *
     * @param requestBusinessParamters 接口所需业务参数 元素集合
     * @return WeixinActionMethodDefine(微信方法定义) weixin action method define
     */
    public WeixinActionMethodDefine addAllRequestBusinessParamters(List<String> requestBusinessParamters) {
        this.requestBusinessParamters.addAll(requestBusinessParamters);
        return this;
    }

    /**
     * 获取 接口所需业务参数
     *
     * @return requestBusinessParamters 接口所需业务参数
     */
    public List<String> getRequestBusinessParamters() {
        return this.requestBusinessParamters;
    }

    /**
     * 设置 接口所需业务参数
     *
     * @param requestBusinessParamters 接口所需业务参数
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setRequestBusinessParamters(List<String> requestBusinessParamters) {
        this.requestBusinessParamters = requestBusinessParamters;
        return this;
    }

    /**
     * 获取 默认异常处理器
     *
     * @return exceptionHandler 默认异常处理器
     */
    public WeixinExceptionHandler getExceptionHandler() {
        return this.exceptionHandler;
    }

    /**
     * 设置 默认异常处理器
     *
     * @param exceptionHandler 默认异常处理器
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setExceptionHandler(WeixinExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }

    /**
     * 新增设置 接口post的参数
     *
     * @param key   接口post的参数 元素 key
     * @param value 接口post的参数 元素 value
     * @return WeixinActionMethodDefine(微信方法定义) weixin action method define
     */
    public WeixinActionMethodDefine putActionPostParamter(String key, String value) {
        this.actionPostParamter.put(key, value);
        return this;
    }

    /**
     * 批量新增设置 接口post的参数
     *
     * @param actionPostParamter 接口post的参数 元素集合
     * @return WeixinActionMethodDefine(微信方法定义) weixin action method define
     */
    public WeixinActionMethodDefine putAllActionPostParamter(Map<String, String> actionPostParamter) {
        this.actionPostParamter.putAll(actionPostParamter);
        return this;
    }

    /**
     * 获取 接口post的参数
     *
     * @return actionPostParamter 接口post的参数
     */
    public Map<String, String> getActionPostParamter() {
        return this.actionPostParamter;
    }

    /**
     * 设置 接口post的参数
     *
     * @param actionPostParamter 接口post的参数
     * @return 返回 WeixinActionMethodDefine(微信方法定义)
     */
    public WeixinActionMethodDefine setActionPostParamter(Map<String, String> actionPostParamter) {
        this.actionPostParamter = actionPostParamter;
        return this;
    }
}

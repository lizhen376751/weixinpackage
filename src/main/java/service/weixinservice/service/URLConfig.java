package service.weixinservice.service;

/**
 * 微信dubbo配置类
 */
public class URLConfig {
    /**
     * 微信项目URLbase
     */
    private String weixinURL = "";


    /**
     * URLConfig(微信dubbo配置类) 字符串形式
     *
     * @return URLConfig(微信dubbo配置类)字符串
     */
    @Override
    public String toString() {
        return "weixinURL:" + weixinURL;
    }

    /**
     * 获取 微信项目URLbase
     *
     * @return weixinURL 微信项目URLbase
     */
    public String getWeixinURL() {
        return this.weixinURL;
    }

    /**
     * 设置 微信项目URLbase
     *
     * @param weixinURL 微信项目URLbase
     * @return 返回 URLConfig(微信dubbo配置类)
     */
    public URLConfig setWeixinURL(String weixinURL) {
        this.weixinURL = weixinURL;
        return this;
    }
}

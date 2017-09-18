package modules.weixin.weixinmessage;

import java.io.Serializable;
import java.util.List;

/**
 * 模板消息
 * Created by lizhen on 2017/4/24.
 */
public class Template implements Serializable {
    /**
     * 消息接收方
     */
    private String toUser;
    /**
     * 模板id
     */
    private String templateId;

    /**
     * 模板消息详情链接
     */
    private String url;
    /**
     * 消息顶部的颜色
     */
    private String topColor;
    /**
     * 参数列表
     */
    private List<TemplateData> templateParamList;

    /**
     * 实体类转换成json数据,并发送到微信服务器上
     *
     * @return 字符串转换为json数据 string
     */
    public String toJSON() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
        buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
        buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
        buffer.append(String.format("\"topcolor\":\"%s\"", this.topColor)).append(",");
        buffer.append("\"data\":{");
        TemplateData param = null;
        for (int i = 0; i < this.templateParamList.size(); i++) {
            param = templateParamList.get(i);
            // 判断是否追加逗号
            if (i < this.templateParamList.size() - 1) {

                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(), param.getColor()));
            } else {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(), param.getColor()));
            }

        }
        buffer.append("}");
        buffer.append("}");
        return buffer.toString();
    }

    /**
     * Template(模板消息) 字符串形式
     *
     * @return Template(模板消息)字符串
     */
    @Override
    public String toString() {
        return "toUser:" + toUser + ",templateId:" + templateId + ",url:" + url + ",topColor:" + topColor + ",templateParamList:" + templateParamList;
    }

    /**
     * 获取 消息接收方
     *
     * @return toUser 消息接收方
     */
    public String getToUser() {
        return this.toUser;
    }

    /**
     * 设置 消息接收方
     *
     * @param toUser 消息接收方
     * @return 返回 Template(模板消息)
     */
    public Template setToUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    /**
     * 获取 模板id
     *
     * @return templateId 模板id
     */
    public String getTemplateId() {
        return this.templateId;
    }

    /**
     * 设置 模板id
     *
     * @param templateId 模板id
     * @return 返回 Template(模板消息)
     */
    public Template setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * 获取 模板消息详情链接
     *
     * @return url 模板消息详情链接
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 模板消息详情链接
     *
     * @param url 模板消息详情链接
     * @return 返回 Template(模板消息)
     */
    public Template setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 获取 消息顶部的颜色
     *
     * @return topColor 消息顶部的颜色
     */
    public String getTopColor() {
        return this.topColor;
    }

    /**
     * 设置 消息顶部的颜色
     *
     * @param topColor 消息顶部的颜色
     * @return 返回 Template(模板消息)
     */
    public Template setTopColor(String topColor) {
        this.topColor = topColor;
        return this;
    }

    /**
     * 获取 参数列表
     *
     * @return templateParamList 参数列表
     */
    public List<TemplateData> getTemplateParamList() {
        return this.templateParamList;
    }

    /**
     * 设置 参数列表
     *
     * @param templateParamList 参数列表
     * @return 返回 Template(模板消息)
     */
    public Template setTemplateParamList(List<TemplateData> templateParamList) {
        this.templateParamList = templateParamList;
        return this;
    }
}

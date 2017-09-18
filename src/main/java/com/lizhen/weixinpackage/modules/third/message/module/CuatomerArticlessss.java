package modules.third.message.module;

import java.io.Serializable;
import java.util.List;

/**
 * 发送图文消息（点击跳转到外链）
 * Created by lizhen on 2017/8/23.
 */

public class CuatomerArticlessss  implements Serializable {
    /**
     * 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     */
    private List<NewsArticles> articles;


    /**
     *  CuatomerArticlessss(发送图文消息（点击跳转到外链）) 字符串形式
     * @return CuatomerArticlessss(发送图文消息（点击跳转到外链）)字符串
     */
    @Override
    public String toString() {
        return "articles:" + articles;
    }

    /**
     * 获取 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     * @return articles 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     */
    public List<NewsArticles> getArticles() {
        return this.articles;
    }

    /**
     * 设置 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     * @param articles 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     * @return 返回 CuatomerArticlessss(发送图文消息（点击跳转到外链）)
     */
    public CuatomerArticlessss setArticles(List<NewsArticles> articles) {
        this.articles = articles;
        return this;
    }
}

package modules.weixin.weixinmessage;

import java.io.Serializable;
import java.util.List;

/**
 * 被动回复图文消息(多图文消息)
 * Created by lizhen on 2017/6/8.
 */
public class NewsMessage extends BaseMessage implements Serializable {
    /**
     * 图文消息个数，限制为10条以内
     */
    private int articleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<Article> articles;


    /**
     * NewsMessage(Created by lizhen on 201768.) 字符串形式
     *
     * @return NewsMessage(Created by lizhen on 201768.)字符串
     */
    @Override
    public String toString() {
        return "articleCount:" + articleCount + ",articles:" + articles;
    }

    /**
     * 获取 图文消息个数，限制为10条以内
     *
     * @return articleCount 图文消息个数，限制为10条以内
     */
    public int getArticleCount() {
        return this.articleCount;
    }

    /**
     * 设置 图文消息个数，限制为10条以内
     *
     * @param articleCount 图文消息个数，限制为10条以内
     * @return 返回 NewsMessage(Created by lizhen on 201768.)
     */
    public NewsMessage setArticleCount(int articleCount) {
        this.articleCount = articleCount;
        return this;
    }

    /**
     * 获取 多条图文消息信息，默认第一个item为大图
     *
     * @return articles 多条图文消息信息，默认第一个item为大图
     */
    public List<Article> getArticles() {
        return this.articles;
    }

    /**
     * 设置 多条图文消息信息，默认第一个item为大图
     *
     * @param articles 多条图文消息信息，默认第一个item为大图
     * @return 返回 NewsMessage(Created by lizhen on 201768.)
     */
    public NewsMessage setArticles(List<Article> articles) {
        this.articles = articles;
        return this;
    }
}
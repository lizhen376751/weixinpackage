package modules.weixin.weixinmessage;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 上传图文材料返回的media_id
 * Created by lizhen on 2017/5/15.
 */
public class Mpnews implements Serializable {
    /**
     * 上传图材料之后返回的media_id
     */
    @JSONField(name = "media_id")
    private String  mediaid;


    /**
     *  Mpnews(上传图文材料返回的media_id) 字符串形式
     * @return Mpnews(上传图文材料返回的media_id)字符串
     */
    @Override
    public String toString() {
        return "mediaid:" + mediaid;
    }

    /**
     * 获取 上传图材料之后返回的media_id
     *
     * @return mediaid 上传图材料之后返回的media_id
     */
    public String getMediaid() {
        return this.mediaid;
    }

    /**
     * 设置 上传图材料之后返回的media_id
     *
     * @param mediaid 上传图材料之后返回的media_id
     * @return 返回 Mpnews(上传图文材料返回的media_id)
     */
    public Mpnews setMediaid(String mediaid) {
        this.mediaid = mediaid;
        return this;
    }
}

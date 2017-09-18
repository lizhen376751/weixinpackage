package service.drivinglicense;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 车险投保行驶证图片上传
 * Created by lizhen on 2017/6/24.
 */
@Service
public class DrivingLicense  {

    public String drivingLicense(String appcode, String body) {
        String fellback = "";
        String host = "https://dm-53.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_vehicle.json";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":" + '"' + body + '"' + "}}]}";
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            fellback = EntityUtils.toString(response.getEntity());
            System.out.println(EntityUtils.toString(response.getEntity()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fellback;
    }
}

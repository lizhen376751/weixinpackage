package service.weixinservice.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 微信图片文件上传
 * Created by lizhen on 2017/7/27.
 */
@Service
public class WeChatCardService {
    /**
     * 日誌打印
     */
    private static Logger log = LoggerFactory.getLogger(WeChatCardService.class);

    /**
     * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
     *
     * @param token    请求地址 form表单url地址
     * @param filePath 文件的
     * @param fileInputStream       文件的
     * @return String url的响应信息返回值
     */

    public String send(String token, String filePath, FileInputStream fileInputStream) {
        log.info("token=" + token + ",filePath=" + filePath + ",in=" + fileInputStream);
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
                + "j3fbhrXYGowMKHxH5E3UBJsPoaYKfgTl6DT-fiieFUEXjDS8xoSzv9uwW56i5COWrKgULUp1ZZw7zHyfEivT2in__JMW4uPCW-pkLNkPpxkPxCLoiP8S88aUlkgG-sqTXCFeAIAOTW&type=image";

        String result = null;


        File file = new File(filePath);
//        if (!file.exists() || !file.isFile()) {
//            throw new IOException("文件不存在");
//        }


        try {
            /**
             * 第一部分
             */
            URL urlObj = new URL(url);
            // 连接
            HttpURLConnection con = null;
            con = (HttpURLConnection) urlObj.openConnection();
            /**
             * 设置关键值
             */
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存


            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");


            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);


            // 请求正文信息


            // 第一部分：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                    + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");


            byte[] head = sb.toString().getBytes("utf-8");


            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);


            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(fileInputStream);

            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();


            // 结尾部分
            byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8"); // 定义最后数据分隔线


            out.write(foot);


            out.flush();
            out.close();


            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            try {
                // 定义BufferedReader输入流来读取URL的响应
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    buffer.append(line);
                }
                if (result == null) {
                    result = buffer.toString();
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常！" + e);
                e.printStackTrace();
                throw new IOException("数据读取异常");
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }

            JSONObject jsonObj = JSONObject.parseObject(result);
            String mediaId = jsonObj.getString("media_id");
            return mediaId;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


package service.weixin.http.service;

import com.alibaba.fastjson.JSONObject;
import modules.weixin.weixinmessage.TeletextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 微信消息群发上传图片以及上传微信消息素材
 */
@Service
public class FileUpload {

    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(FileUpload.class);

    /**
     * main方法,主要用于测试
     *
     * @param args 数组
     * @throws IOException 网络异常
     */
    public static void main(String[] args) throws IOException {
        String filePath = "";
        String accessToken = "";
        FileUpload fileUpload = new FileUpload();
        String result = fileUpload.uploadImage(accessToken, filePath);
        System.out.println(result);

    }

    /**
     * @param inStream inStream
     * @return byte[]
     * @throws IOException IOException
     */
    private byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
     *
     * @param accessToken 微信的token
     * @param filePath    文件在服务器保存路径
     * @return String url的响应信息返回值
     * @throws IOException 网络异常
     */
    public String uploadImage(String accessToken, String filePath) throws IOException {
        //ACCESS_TOKEN是获取到的access_token
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=image".replace("ACCESS_TOKEN", accessToken);
        String result = null;
        //判断路径是否正确,以及是否是图片
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            URL url2 = new URL(filePath);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            file = new File("test.jpg");
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(file);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
            if (!file.exists() || !file.isFile()) {
                throw new IOException("文件不存在");
            }
        }

        /**
         * 第一部分
         */
        URL urlObj = new URL(url);
        // 建立连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
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
        String bOUNDARY = "---------------------------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                + bOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(bOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);
        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + bOUNDARY + "--\r\n").getBytes("utf-8"); // 定义最后数据分隔线
        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
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
        log.info("上传图片返回结果为======================" + result);
//        {"type":"image","media_id":"1Le_MCBJShdaL1nLirSFRkYYiEXtPTUD_uOk0D9QtNLchWiQ2MHOtkT4Rm6T9Ciy","created_at":1447392539}
        String mediaid = JSONObject.parseObject(result).getString("media_id");
        return mediaid;
    }

    /**
     * 上传图文素材
     *
     * @param accessToken     微信的token
     * @param teletextMessage 微信的消息
     * @return 上传的相关参数
     */
    public String uploadTextMessage(String accessToken, TeletextMessage teletextMessage) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
        String jsonteletextMessage = JSONObject.toJSONString(teletextMessage);
        String jsonResult = null;
        try {
            jsonResult = HttpUtils.sendPostJson(url, jsonteletextMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("上传图文消息素材的返回的结果为=========================" + jsonResult);
        String textMessageId = JSONObject.parseObject(jsonResult).getString("media_id");
        return textMessageId;
    }
}

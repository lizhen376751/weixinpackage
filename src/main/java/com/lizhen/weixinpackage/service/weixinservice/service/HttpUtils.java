package com.lizhen.weixinpackage.service.weixinservice.service;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * 微信基础通讯的包装
 * Created by lizhen on 2017/4/14.
 */
public final class HttpUtils {
    /**
     * 微信基础url共同的
     */
    private static final String BASE_URL = "https://api.weixin.qq.com";
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 隐藏工具类
     */
    private HttpUtils() {
    }

    /**
     * 模板消息发送,创建菜单
     *
     * @param urls   urls
     * @param params params
     * @return String
     * @throws IOException IOException
     * @Description: http post请求json数据(入参和接受都是json)
     */
    public static String sendPostJson(String urls, String params) throws IOException {
        HttpPost request = new HttpPost(urls);
        //创建带字符创参数和字符编码的
        StringEntity se = new StringEntity(params, HTTP.UTF_8);
        request.setEntity(se);
        // 发送请求
        HttpResponse httpResponse = new DefaultHttpClient().execute(request);
        // 得到应答的字符串，这也是一个 JSON 格式保存的数据
        String retSrc = EntityUtils.toString(httpResponse.getEntity());
        request.releaseConnection();
        log.info("Post请求返回结果为=========================" + retSrc);
        return retSrc;

    }

    /**
     * 发送微信消息
     *
     * @param urlStr  urlStr
     * @param xmlInfo xmlInfo
     * @return String
     * @Description: http请求发送xml内容
     */
    public static String sendXmlPost(String urlStr, String xmlInfo) {
        // xmlInfo xml具体字符串
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
//            con.setRequestProperty("Pragma:", "no-cache");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String lines = "";
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                lines = lines + line;
            }
            log.info("发送微信消息返回结果为:================================" + lines);
            return lines; // 返回请求结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * 企业付款以及查询需要证书
     * @param url api接口路径
     * @param xmlInfo xml信息
     * @param mchid 商户id
     * @return 请求信息
     * 容易出现jar包冲突 httpcore-4.3.2 需要将jar包替换成这个版本的
     */
    public static String ssl(String url,String xmlInfo,String mchid){
        //以下是需要ssl证书的
        StringBuffer message = new StringBuffer();
        try {
            //指定读取证书格式为PKCS12
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            //读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream(new File("D:/cert/apiclient_cert.p12"));
            //指定PKCS12的密码(商户ID)
            keyStore.load(instream, mchid.toCharArray());

            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, mchid.toCharArray())
                    .build();
            //指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[] { "TLSv1" },
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            //设置httpclient的SSLSocketFactory
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
            HttpPost httpost = new HttpPost(url);

            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(xmlInfo, "UTF-8"));
            //System.out.println("executing request" + httpost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();

                //System.out.println("----------------------------------------");
                //System.out.println(response.getStatusLine());
                if (entity != null) {
                    //System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        message.append(text);
                    }
                }
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return message.toString();
    }


    /**
     * @param reqUrl reqUrl
     * @param params params
     * @return String
     * @throws Exception Exception
     * @Description: http get请求共用方法
     * @author
     * @date
     */
    public static String sendGet(String reqUrl, Map<String, String> params) throws Exception {
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        try {
            String url = "";
            if (null == params || "".equals(params) || "null".equals(params)) {
                url = reqUrl;
            } else {
                url = buildUrl(reqUrl, params);
            }
            // 1. 创建 HttpClient 的实例
            HttpClient client = new DefaultHttpClient();
            //2. 创建某种连接方法的实例,构造函数中传入待连接的地址
            request.setHeader("Accept-Encoding", "gzip");
            request.setURI(new URI(url));
            // 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
            HttpResponse response = client.execute(request);
            //4. 读 response
            inputStream = response.getEntity().getContent();
            //6. 对得到后的内容进行处理
            String result = getJsonStringFromGZIP(inputStream);
            return result;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            //5. 释放连接。无论执行方法是否成功，都必须释放连接
            request.releaseConnection();
        }

    }

    /**
     * @param reqUrl reqUrl
     * @param params params
     * @return String
     * @throws Exception Exception
     * @Description: http post请求共用方法
     */
    public static String sendPost(String reqUrl, Map<String, String> params)
            throws Exception {
        try {
            //将获取的参数放入一个set集合
            Set<String> set = params.keySet();
            //在发送post请求时用该list来存放参数(类似于key和value)
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            //遍历set集合
            for (String key : set) {
                //存入list里面
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
            log.info("http post传过来参数为===" + params.toString() + "http post封装参数为===" + list.toString());
            if (list.size() > 0) {
                try {
                    //HttpClient 提供的主要的功能，（1）实现了所有 HTTP 的方法（GET,POST,PUT,HEAD 等）（2）支持代理服务器等
                    // 1. 创建 HttpClient 的实例
                    HttpClient client = new DefaultHttpClient();
                    //2. 创建某种连接方法的实例,构造函数中传入待连接的地址
                    HttpPost request = new HttpPost(reqUrl);
                    request.setHeader("Accept-Encoding", "gzip");
                    request.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
                    // 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
                    HttpResponse response = client.execute(request);
                    //4. 读 response
                    InputStream inputStream = response.getEntity().getContent();
                    try {
                        //6. 对得到后的内容进行处理
                        String result = getJsonStringFromGZIP(inputStream);
                        log.info("http post请求共用方法返回结果为===" + result);
                        return result;
                    } finally {
                        //5. 释放连接。无论执行方法是否成功，都必须释放连接
                        inputStream.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    log.info("http post请求共用方法返回异常为===" + ex.getMessage());
                    throw new Exception("网络连接失败,请连接网络后再试");
                }
            } else {
                throw new Exception("参数不全，请稍后重试");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("http post请求共用方法返回异常为===" + ex.getMessage());
            throw new Exception("发送未知异常");
        }
    }

    /**
     * 对请求结果进行格式处理
     *
     * @param is 请求结果字符流
     * @return String
     */
    private static String getJsonStringFromGZIP(InputStream is) {
        String jsonString = null;
        log.info("对请求结果进行格式处理" + is.toString());
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            // 判断是否是GZIP格式
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b) {
                // LogUtil.i("HttpTask", " use GZIPInputStream  ");
                is = new GZIPInputStream(bis);
            } else {
                // LogUtil.d("HttpTask", " not use GZIPInputStream");
                is = bis;
            }
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            jsonString = sb.toString();
            bis.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    /**
     * @param data data
     * @return int
     */
    private static int getShort(byte[] data) {
        return (data[0] << 8) | data[1] & 0xFF;
    }
    //暂时被取代=======================================================

    /**
     * 构建get方式的url
     *
     * @param reqUrl 基础的url地址
     * @param params 查询参数
     * @return 构建好的url
     */
    public static String buildUrl(String reqUrl, Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key : set) {
            query.append(String.format("%s=%s&", key, params.get(key)));
        }
        return reqUrl + "?" + query.toString();
    }


    //===================================================================


}

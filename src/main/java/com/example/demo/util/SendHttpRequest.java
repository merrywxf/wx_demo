package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SendHttpRequest {
    /**
     * @param url
     * @param
     * @return
     */
    public static JSONObject sendGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpGet method = new HttpGet(url);

        HttpHost proxy = new HttpHost("172.16.8.67", 3128);
        // 依次是代理地址，代理端口号，协议类型
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(3000)
                .build();
       // method.setConfig(requestConfig);
        try {
            CloseableHttpResponse result = httpclient.execute(method);
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    str = EntityUtils.toString(result.getEntity());
                    System.out.println(str);
                    jsonResult = (JSONObject) JSONObject.parse(str);
                } catch (Exception e) {
                    System.out.println("get请求提交失败:" + url);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("get请求提交失败:" + url);
        }
        return jsonResult;
    }
}

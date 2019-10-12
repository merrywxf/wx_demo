package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Token {

    private static String access_token = "";

    private static String jsapi_ticket = "";

    public static int time = 0;

    private static int expires_in = 7200;

    static {
        Thread t = new Thread(new Runnable() {
            public void run() {
                do {
                    time++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        });
        t.start();
    }

//    public static String getToken(String url) {
//        if ("".equals(access_token) || access_token == null) {
//            send(url);
//        } else if (time > expires_in) {
//            //当前token已经失效，从新获取信息
//            send(url);
//        }
//        return access_token;
//    }

    /**
     * 第二种获取方法
     */
    public static String getSignature(String url,String ticket_url) throws UnsupportedEncodingException {
        String ticket = Token.getTicket(url,ticket_url);
        String noncestr = RandomStringGenerator.getRandomStringByLength(32);
        long timestamp = new Date().getTime() / 1000;
        //请勿更换字符组装顺序
        String sign = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        //url为你当前访问的url路径，除去#与#后面的数据
        String signature = new SHA1().getDigestOfString(sign.getBytes("utf-8"));
        return signature;
    }

    //String url = wxConfig.server_token_url + "&appid=" + wxConfig.appid + "&secret=" + wxConfig.appsecret;
    public static String getTicket(String token_url, String ticket_url) {
        if ("".equals(access_token) || access_token == null || time > expires_in) {
            JSONObject json = HttpUtil.sendGet(token_url);
            access_token = json.getString("access_token");
            ticket_url=String.format(ticket_url, access_token);
            // "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
            jsapi_ticket = HttpUtil.sendGet(ticket_url).getString("ticket");
            time = 0;
            return jsapi_ticket;
        } else {
            return jsapi_ticket;
        }
    }
    public static void getCode(String auth_url){
//        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        HttpUtil.sendGet(auth_url);
    }
}

package com.example.demo.controller;

import com.example.demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private WXConfig wxConfig;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/wx_sign")
    @ResponseBody
    public Map<String, String> wx_sign(HttpServletRequest request) throws UnsupportedEncodingException {
        String token_url = String.format(wxConfig.getAccessTokenUrl(), wxConfig.getAppid(), wxConfig.getAppsecret());
        String jsapi_ticket = Token.getTicket(token_url,wxConfig.getTicketUrl());
        String contextUrl = request.getRequestURL().toString();
        System.out.println(contextUrl+"===================url===");
//        contextUrl="http://merry.f3322.net:6102";
        Map<String, String> ret = WXSign.sign(jsapi_ticket, contextUrl);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        ret.put("appId", wxConfig.getAppid());
//        String backUrl= URLEncoder.encode("http://218.12.25.82:6102/userAutho","UTF-8");
//String auth_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxConfig.getAppid()+"&redirect_uri="+backUrl+"&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//    Token.getCode(auth_url);

        return ret;
    }
    @RequestMapping("/userAutho")
    public void UserInfo(HttpServletRequest request) {
        System.out.println("==========================");
        System.out.println(request.getRequestURL().toString());
    }
    @RequestMapping("/step")
    public String step(String aid) {
        System.out.println(aid + "=============================");
        return "step" + aid;
    }
}

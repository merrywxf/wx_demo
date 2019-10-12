package com.example.demo.controller;

import com.example.demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private WXConfig wxConfig;
//    @RequestMapping("/")
//    @ResponseBody
//    public String index(String echostr) {
//        return echostr;
//    }
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @PostMapping("/wx_sign")
    @ResponseBody
    public Map<String, String> wx_sign(HttpServletRequest request) throws UnsupportedEncodingException {
        String token_url = String.format(wxConfig.getAccessTokenUrl(), wxConfig.getAppid(), wxConfig.getAppsecret());
        String jsapi_ticket = Token.getTicket(token_url,wxConfig.getTicketUrl());
        String contextUrl = request.getRequestURL().toString();
        contextUrl=contextUrl.substring(0,contextUrl.lastIndexOf("/")+1);
        System.out.println(contextUrl+"===================url===");
        Map<String, String> ret = WXSign.sign(jsapi_ticket, contextUrl);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        ret.put("appId", wxConfig.getAppid());
        return ret;
    }
    @GetMapping("/location")
    @ResponseBody
    public String UserInfo(HttpServletRequest request,String latitude,String longitude) {
        System.out.println(latitude+"=================hello world=========");
        System.out.println(longitude+"=================hello world=========");
        return "ok";
    }
    @RequestMapping("/step")
    public String step(String aid) {
        System.out.println(aid + "=============================");
        return "step/step" + aid;
    }
}

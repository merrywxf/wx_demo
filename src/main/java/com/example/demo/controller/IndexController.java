package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.util.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private WXConfig wxConfig;

    @Autowired
    private VisitLogRepository visitLogRepository;

    /**
     * @param visitId 上一个分享者
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model, String visitId) {
        String url = request.getRequestURL().toString();
        logger.info(url + "==============================");
        String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        VisitLog visitLog = new VisitLog();
        visitLog.setUserIp(HttpUtil.getIpAddress(request));
        visitLog.setDateTime(date);
        visitLog.setLastShareVisitId(visitId);
        visitLogRepository.save(visitLog);
        Long visitId_new = visitLog.getId();
        model.addAttribute("visitId", visitId_new);
        return "index";
    }

    @PostMapping("/wx_sign")
    @ResponseBody
    public Map<String, String> wx_sign(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        url = URLDecoder.decode(url, "UTF-8");
        logger.info(url + "===================");
        String token_url = String.format(wxConfig.getAccessTokenUrl(), wxConfig.getAppid(), wxConfig.getAppsecret());
        String jsapi_ticket = Token.getTicket(token_url, wxConfig.getTicketUrl());
        Map<String, String> ret = WXSign.sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        ret.put("appId", wxConfig.getAppid());
        return ret;
    }

    @RequestMapping("/step")
    public String step(String aid) {
        logger.info(aid + "=============================");
        return "step/step" + aid;
    }
}

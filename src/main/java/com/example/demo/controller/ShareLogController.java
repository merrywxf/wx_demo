package com.example.demo.controller;

import com.example.demo.model.ShareLog;
import com.example.demo.repository.ShareLogRepository;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.PageUtil;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/share")
@ResponseBody
public class ShareLogController {
    @Autowired
    ShareLogRepository shareLogRepository;

    @GetMapping("/save")
    public String save(HttpServletRequest request, String status, String type) {
        ShareLog shareLog = new ShareLog();
        String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        shareLog.setDateTime(date);
        shareLog.setStatus(status);
        shareLog.setUserIp(HttpUtil.getIpAddress(request));
        shareLog.setType(type);
        shareLogRepository.save(shareLog);
        return "ok";
    }

    @GetMapping(value = "shares")
    //currentPage 当前页，默认为0，如果传1的话是查的第二页数据
    //pageSize 每页数据条数
    public Page<ShareLog> findAll(@RequestParam(value = "currentPage") int currentPage, @RequestParam(value = "pageSize") int pageSize) {
        Pageable pageable = PageUtil.getPageable(currentPage, pageSize, "dateTime");
        return shareLogRepository.findAll(pageable);
    }
}

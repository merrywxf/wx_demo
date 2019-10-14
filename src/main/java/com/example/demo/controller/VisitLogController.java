package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.PageUtil;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/visit")
@ResponseBody
public class VisitLogController {
    private Logger logger = LoggerFactory.getLogger(VisitLogController.class);
    @Autowired
    VisitLogRepository visitLogRepository;

    @GetMapping("/update")
    public String update(VisitLog visitLog) {
        logger.info("update visit location");
        visitLogRepository.update( visitLog.getId(),visitLog.getLatitude(), visitLog.getLongitude());
        return String.valueOf(visitLog.getId());
    }
    @GetMapping(value = "visits")
    //currentPage 当前页，默认为0，如果传1的话是查的第二页数据
    //pageSize 每页数据条数
    public Page<VisitLog> findAll(@RequestParam(value = "currentPage") int currentPage, @RequestParam(value = "pageSize") int pageSize) {
        Pageable pageable = PageUtil.getPageable(currentPage, pageSize, "dateTime");
        return visitLogRepository.findAll(pageable);
    }
}

package com.example.demo.repository;

import com.example.demo.model.VisitLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import javax.transaction.Transactional;
import java.util.List;

public interface VisitLogRepository extends CrudRepository<VisitLog, Long> {
    Page<VisitLog> findAll(@PageableDefault(value = 15, sort = {"dateTime"}, direction = Sort.Direction.DESC) Pageable pageable);

//    @Transactional
//    @Modifying
//    @Query("update VisitLog set latitude=#{#visitLog.latitude} , longitude=#{#visitLog.longitude}, adCode=#{#visitLog.adCode},cityCode=#{#visitLog.cityCode} where id=#{#visitLog.id}")
//    void update(@Param("visitLog") VisitLog visitLog);
//
//    @Query("update HuaYangArea hy set " +
//            "hy.areaName = CASE WHEN :#{#huaYangArea.areaName} IS NULL THEN hy.areaName ELSE :#{#huaYangArea.areaName} END ," +
//            "hy.areaPerson = CASE WHEN :#{#huaYangArea.areaPerson} IS NULL THEN hy.areaPerson ELSE :#{#huaYangArea.areaPerson} END ," +
//            "hy.updateDate = CASE WHEN :#{#huaYangArea.updateDate} IS NULL THEN hy.updateDate ELSE :#{#huaYangArea.updateDate} END ," +
//            "hy.updateId =  CASE WHEN :#{#huaYangArea.updateId} IS NULL THEN hy.updateId ELSE :#{#huaYangArea.updateId} END " +
//            "where hy.uid = :#{#huaYangArea.uid}")
//    int update(@Param("huaYangArea") HuaYangArea huaYangArea);
}

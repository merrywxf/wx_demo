package com.example.demo.repository;

import com.example.demo.model.VisitLog;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import javax.transaction.Transactional;

public interface VisitLogRepository extends CrudRepository<VisitLog, Long> {
    Page<VisitLog> findAll(@PageableDefault(value = 15, sort = {"dateTime"}, direction = Sort.Direction.DESC) Pageable pageable);
    @Transactional
    @Modifying
    @Query("update VisitLog set latitude=:latitude , longitude=:longitude where id=:id")
    void update(@Param("id") Long id,@Param("latitude") String latitude,@Param("longitude") String longitude);
}

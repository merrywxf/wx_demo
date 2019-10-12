package com.example.demo.repository;

import com.example.demo.model.ShareLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;

public interface ShareLogRepository  extends CrudRepository<ShareLog, Long> {
    Page<ShareLog> findAll(@PageableDefault(value = 15, sort = {"dateTime"}, direction = Sort.Direction.DESC) Pageable pageable);

}

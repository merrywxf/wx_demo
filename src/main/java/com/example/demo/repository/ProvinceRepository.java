package com.example.demo.repository;

import com.example.demo.model.AddressProvince;
import com.example.demo.model.ShareLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;

public interface ProvinceRepository extends CrudRepository<AddressProvince, Long> {

}

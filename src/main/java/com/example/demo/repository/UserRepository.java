package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select * from t_user where name like %?1%", nativeQuery = true)
    List<User> findByNameLike(String name);

    Page<User> findAll(@PageableDefault(value = 15, sort = {"datetime"}, direction = Sort.Direction.DESC) Pageable pageable);
}

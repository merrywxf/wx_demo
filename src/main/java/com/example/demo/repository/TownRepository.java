package com.example.demo.repository;

import com.example.demo.model.AddressTown;
import org.springframework.data.repository.CrudRepository;

public interface TownRepository extends CrudRepository<AddressTown, Long> {

}

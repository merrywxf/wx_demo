package com.example.demo.repository;

import com.example.demo.model.AddressCity;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<AddressCity, Long> {

}

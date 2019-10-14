package com.example.demo;

import com.example.demo.model.AddressProvince;
import com.example.demo.model.AddressCity;
import com.example.demo.model.AddressTown;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.repository.TownRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TownRepository townRepository;

    @Test
    public void test01() {
        AddressProvince province = provinceRepository.findById(1L).get();
        System.out.println(province.getName());
    }

    @Test
    public void test02() {
        AddressCity city = cityRepository.findById(1L).get();
        System.out.println(city.getName()+"   "+city.getProvinceCode());
    }

    @Test
    public void test03() {
        AddressTown province = townRepository.findById(1L).get();
        System.out.println(province.getName());
    }
}

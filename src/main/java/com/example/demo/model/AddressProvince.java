package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_address_province")
@Data
public class AddressProvince {
    @Id
    private Long id;
    private String code;
    private String name;
}

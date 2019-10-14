package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_address_city")
@Data
public class AddressCity {
    @Id
    private Long id;
    private String code;
    private String name;
    private String provinceCode;

}

package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_address_town")
@Data
public class AddressTown {
    @Id
    private Long id;
    private String code;
    private String name;
    private String cityCode;

}

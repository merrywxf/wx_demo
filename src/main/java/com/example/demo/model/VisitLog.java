package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_visit")
@Data
public class VisitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userIp;
    private String type;
    private String dateTime;
    private String latitude;
    private String longitude;
}

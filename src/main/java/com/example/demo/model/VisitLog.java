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
    private String lastShareVisitId;
    //城市码表
    private String cityCode;
    //区域码表
    private String adCode;
}

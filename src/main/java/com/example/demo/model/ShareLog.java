package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_share")
@Data
public class ShareLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userIp;
    //类型（朋友0  朋友圈1）
    private String type;
    //成功0 失败1
    private String status;
    private String errorMsg;
    private String dateTime;
}

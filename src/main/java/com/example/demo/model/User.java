package com.example.demo.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
@Data
public class User extends JpaRepositoriesAutoConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String id_no;
    private String birth;
    private String sex;
    private String address;
    private String email;
    private String mobile;
    private String datetime;
    // 省略get&set
}

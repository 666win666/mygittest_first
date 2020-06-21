package com.travel.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体类
 */
@Entity
@Table(name = "tab_Admin")
@Data
public class Admin implements Serializable {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;//用户id
    @Column(name = "username")
    private String username;//用户名，账号
    @Column(name = "password")
    private String password;//密码
    @Column(name = "name")
    private String name;//真实姓名
    @Column(name = "birthday")
    private String birthday;//出生日期
    @Column(name = "sex")
    private String sex;//男或女
    @Column(name = "telephone")
    private String telephone;//手机号
    @Column(name = "email")
    private String email;//邮箱
    @Column(name = "status")
    private String status;//激活状态，Y代表激活，N代表未激活
    @Column(name = "code")
    private String code;//激活码（要求唯一）


}

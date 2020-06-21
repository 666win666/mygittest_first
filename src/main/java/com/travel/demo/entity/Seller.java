package com.travel.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商家实体类
 */
@Entity
@Table(name = "tab_Seller")
@Data
public class Seller implements Serializable {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;//商家id
    @Column(name = "sname")
    private String sname;//商家名称
    @Column(name = "consphone")
    private String consphone;//商家电话
    @Column(name = "address")
    private String address;//商家地址


}

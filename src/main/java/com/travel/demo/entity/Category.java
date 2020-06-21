package com.travel.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 分类实体类
 */
@Entity
@Table(name = "tab_category")
@Data
public class Category implements Serializable {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;//分类id

    @Column(name = "CNAME")
    private String cname;//分类名称


}

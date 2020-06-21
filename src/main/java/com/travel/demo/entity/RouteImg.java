package com.travel.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 旅游线路图片实体类
 */
@Entity
@Table(name = "tab_RouteImg")
@Data
public class RouteImg implements Serializable {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rgid;//商品图片id
    @Column(name = "rid")
    private int rid;//旅游商品id
    @Column(name = "bigPic")
    private String bigPic;//详情商品大图
    @Column(name = "smallPic")
    private String smallPic;//详情商品小图


}

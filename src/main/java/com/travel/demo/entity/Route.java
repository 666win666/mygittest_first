package com.travel.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 旅游线路商品实体类
 */
@Entity
@Table(name = "tab_Route")
@Data
public class Route implements Serializable {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;//线路id，必输
    @Column(name = "RNAME")
    private String rname;//线路名称，必输
    @Column(name = "price")
    private double price;//价格，必输
    @Column(name = "ROUTEINTRODUCE")
    private String routeIntroduce;//线路介绍
    @Column(name = "rflag")
    private String rflag;   //是否上架，必输，0代表没有上架，1代表是上架
    @Column(name = "rdate")
    private String rdate;   //上架时间
    @Column(name = "ISTHEMETOUR")
    private String isThemeTour;//是否主题旅游，必输，0代表不是，1代表是
    @Column(name = "count")
    private int count;//收藏数量
    @Column(name = "cid")
    private int cid;//所属分类，必输
    @Column(name = "rimage")
    private String rimage;//缩略图
    @Column(name = "sid")
    private int sid;//所属商家
    @Column(name = "sourceId")
    private String sourceId;//抓取数据的来源id
//    @Column(name = "category")
    @ManyToOne()
    @JoinColumn(name = "cid",insertable=false,updatable=false)
    private Category category;//所属分类
//    @Column(name = "seller")
//    private Seller seller;//所属商家
//    @Column(name = "user")
    @ManyToOne()
    @JoinColumn(name = "sid",insertable=false,updatable=false)
    private User user;//所属商家
//    @Column(name = "routeImgList")
//    private List<RouteImg> routeImgList;//商品详情图片列表


}

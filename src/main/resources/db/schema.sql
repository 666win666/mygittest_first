/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/2/14 23:10:29                           */
/*==============================================================*/
set names utf8;

drop table if exists tab_favorite;
drop table if exists tab_route_img;
drop table if exists tab_route;
drop table if exists tab_category;
drop table if exists tab_seller;
drop table if exists tab_user;
drop table if exists tab_admin;

/*==============================================================*/
/* Table: tab_category                                          */
/*==============================================================*/
create table tab_category
(
   cid                  int not null auto_increment,
   cname                varchar(100) not null,
   primary key (cid),
   unique key AK_nq_categoryname (cname)
);

/*==============================================================*/
/* Table: tab_favorite                                          */
/*==============================================================*/
create table tab_favorite
(
   rid                  int not null,
   date                 date not null,
   uid                  int not null,
   primary key (rid, uid)
);

/*==============================================================*/
/* Table: tab_route                                             */
/*==============================================================*/
create table tab_route
(
   rid                  int not null auto_increment,
   rname                varchar(500) not null,
   price                double not null,
   routeIntroduce       varchar(1000) not null,
   rflag                char(1) not null,
   rdate                varchar(19),
   isThemeTour          char(1) not null,
   count                int default 0,
   cid                  int not null,
   rimage               varchar(200),
   sid                  int,
   sourceId             varchar(50),
   primary key (rid),
   unique key AK_nq_sourceId (sourceId)
);

/*==============================================================*/
/* Table: tab_route_img                                         */
/*==============================================================*/
create table tab_route_img
(
   rgid                 int not null auto_increment,
   rid                  int not null,
   bigPic               varchar(200) not null,
   smallPic             varchar(200),
   primary key (rgid)
);

/*==============================================================*/
/* Table: tab_seller                                            */
/*==============================================================*/
create table tab_seller
(
   sid                  int not null auto_increment,
   sname                varchar(200) not null,
   consphone            varchar(20) not null,
   address              varchar(200),
   primary key (sid),
   unique key AK_Key_2 (sname)
);

/*==============================================================*/
/* Table: tab_user                                              */
/*==============================================================*/
create table tab_user
(
   uid                  int not null auto_increment,
   username             varchar(100) not null,
   password             varchar(32) not null,
   name                 varchar(100),
   birthday             varchar(20),
   sex                  char(1),
   telephone            varchar(11),
   email                varchar(100),
   status               char(1) ,
   code					varchar(50),

   primary key (uid),
   unique key AK_nq_username (username),
   unique key AK_nq_code (code)
);



/*==============================================================*/
/* Table: tab_admin                                              */
/*==============================================================*/
create table tab_admin
(
   uid                  int not null auto_increment,
   username             varchar(100) not null,
   password             varchar(32) not null,
   name                 varchar(100),
   birthday             varchar(20),
   sex                  char(1),
   telephone            varchar(11),
   email                varchar(100),
   status               char(1) ,
   code					varchar(50),

   primary key (uid),
   unique key AD_nq_username (username),
   unique key AD_nq_code (code)
);
-- alter table tab_favorite add constraint FK_route_favorite foreign key (rid)
--       references tab_route (rid) on delete restrict on update restrict;
--
-- alter table tab_favorite add constraint FK_user_favorite foreign key (uid)
--       references tab_user (uid) on delete restrict on update restrict;
--
-- alter table tab_route add constraint FK_category_route foreign key (cid)
--       references tab_category (cid) on delete restrict on update restrict;
--
-- alter table tab_route add constraint FK_seller_route foreign key (sid)
--       references tab_seller (sid) on delete restrict on update restrict;
--
-- alter table tab_route_img add constraint FK_route_routeimg foreign key (rid)
--       references tab_route (rid) on delete restrict on update restrict;


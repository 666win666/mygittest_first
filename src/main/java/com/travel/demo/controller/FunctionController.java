package com.travel.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FunctionController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/header")
    public String header() {
        return "header";
    }
    @RequestMapping("/footer")
    public String footer() {
        return "footer";
    }
    @RequestMapping("/register_ok")
    public String registerOk() {
        return "register_ok";
    }
    @RequestMapping("/route_list")
    public String routeList(String cid,String rname) {
        return "route_list";
    }
    @RequestMapping("/route_detail")
    public String routeDetail() {
        return "route_detail";
    }
    @RequestMapping("/myfavorite")
    public String myfavorite() {
        return "myfavorite";
    }
    @RequestMapping("/favoriterank")
    public String favoriterank() {
        return "favoriterank";
    }
    @RequestMapping("/admin_data")
    public String adminData() {
        return "admin_data";
    }
    @RequestMapping("/user_data")
    public String userData() {
        return "user_data";
    }
    @RequestMapping("/admin_login")
    public String admin_login() {
        return "admin_login";
    }
    @RequestMapping("/admin_register")
    public String admin_register() {
        return "admin_register";
    }
    @RequestMapping("/luntan")
    public String luntan() {
        return "luntan";
    }
    @RequestMapping("/admin_info")
    public String admin_info() {
        return "admin_info";
    }
    @RequestMapping("/user_info")
    public String user_info() {
        return "user_info";
    }
    @RequestMapping("/category")
    public String category1( String cid) {
        return "category"+cid;
    }


    @RequestMapping("/exitServlet")
    public String exitServlet(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
    @RequestMapping("/route_save_ok")
    public String route_save_ok() {
        return "route_save_ok";
    }
}

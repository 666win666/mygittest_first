package com.travel.demo.controller;


import com.travel.demo.entity.ResultInfo;
import com.travel.demo.entity.Admin;
import com.travel.demo.service.AdminServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping("/findOne")
    public Admin findOne(HttpServletRequest request) {
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null) {
            return null;
        }
        return (Admin) admin;
    }

    @RequestMapping("/login")
    public ResultInfo login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装Admin对象
        Admin admin = new Admin();
        admin.setUsername(map.get("username")[0]);
        admin.setPassword(map.get("password")[0]);
        String check = map.get("check")[0];
        String checkCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        if (StringUtils.isEmpty(checkCode) || !checkCode.equals(check)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        } else {

            //3.调用Service查询
            Admin u = adminService.login(admin);

            //4.判断用户对象是否为null
            if (u == null) {
                //用户名密码或错误
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误");
            }
            //5.判断用户是否激活
            if (u != null && !"Y".equals(u.getStatus())) {
                //用户尚未激活
                info.setFlag(false);
                info.setErrorMsg("您尚未激活，请激活");
            }
            //6.判断登录成功
            if (u != null && "Y".equals(u.getStatus())) {
                request.getSession().setAttribute("admin", u);//登录成功标记

                //登录成功
                info.setFlag(true);
            }
        }

        return info;

    }


    @RequestMapping("/regist")
    public ResultInfo register(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        ResultInfo info = new ResultInfo();
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        } else {

            //1.获取数据
            Map<String, String[]> map = request.getParameterMap();


            //2.封装对象
            Admin admin = new Admin();
            try {
                BeanUtils.populate(admin, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            //3.调用service完成注册
            boolean flag = adminService.regist(admin);
            //4.响应结果
            if (flag) {
                //注册成功
                info.setFlag(true);
            } else {
                //注册失败
                info.setFlag(false);
                info.setErrorMsg("注册失败!");
            }
        }
        return info;

    }

}

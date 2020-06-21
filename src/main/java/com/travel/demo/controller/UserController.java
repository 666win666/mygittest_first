package com.travel.demo.controller;


import com.travel.demo.entity.ResultInfo;
import com.travel.demo.entity.User;
import com.travel.demo.service.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/findOne")
    public User findOne(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }
        return (User) user;
    }

    @RequestMapping("/login")
    public ResultInfo login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装User对象
        User user = new User();
        user.setUsername(map.get("username")[0]);
        user.setPassword(map.get("password")[0]);
        String check = map.get("check")[0];
        String checkCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        if (StringUtils.isEmpty(checkCode) || !checkCode.equals(check)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        } else {

            //3.调用Service查询
            User u = userService.login(user);

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
                request.getSession().setAttribute("user", u);//登录成功标记

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
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            //3.调用service完成注册
            boolean flag = userService.regist(user);
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

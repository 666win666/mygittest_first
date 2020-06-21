package com.travel.demo.controller;


import com.travel.demo.entity.ResultInfo;
import com.travel.demo.entity.Route;
import com.travel.demo.entity.User;
import com.travel.demo.service.RouteService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping("/findAll")
    public List<Route> findAll(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }
        return routeService.findAll((User) user);
    }

    @RequestMapping("/pageQuery")
    public Page<Route> pageQuery(String cid,String currentPage,String rname) {


        Integer categoryId=StringUtils.isEmpty(cid)?0:Integer.parseInt(cid);
        Integer currentPage2=StringUtils.isEmpty(currentPage)?1:Integer.parseInt(currentPage);
        if(StringUtils.isEmpty(rname)){
            return Page.empty();
        }
        String rname2=(String)(rname);

        return routeService.pageQuery(rname2,currentPage2-1);
    }

    @RequestMapping("/approve/{rid}")
    public String approve(@PathVariable String rid) {
        return routeService.updateByRid(rid,"1");
    }

    @RequestMapping("/reject/{rid}")
    public String reject(@PathVariable String rid) {
        return routeService.updateByRid(rid,"2");
    }

    @RequestMapping("/findAllRoute")
    public List<Route> findAllRoute() {

        return routeService.findByRflag();
    }

    @RequestMapping("/save")
    public ResultInfo save(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            Route route = new Route();
            try {
                BeanUtils.populate(route, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Object user = request.getSession().getAttribute("user");
            if (user == null) {
                info.setErrorMsg("未登录，请先登录");
            } else {
                User u = (User) user;
                route.setSid(u.getUid());
                route.setRdate(new Date().toString().substring(0,18));
                route.setIsThemeTour(Optional.ofNullable(route.getIsThemeTour()).orElse("1"));
                route.setCid(Optional.ofNullable(route.getCid()).orElse(2));
                route.setRflag("0");
                //3.调用service完成注册
                boolean flag = routeService.save(route);
                //4.响应结果
                if (flag) {
                    //注册成功
                    info.setFlag(true);
                } else {
                    //注册失败
                    info.setFlag(false);
                    info.setErrorMsg("申请失败!");
                }
            }


        }
        return info;

    }


}

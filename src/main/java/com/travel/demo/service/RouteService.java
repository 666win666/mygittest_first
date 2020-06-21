package com.travel.demo.service;


import com.travel.demo.dao.CategoryDao;
import com.travel.demo.dao.RouteDao;
import com.travel.demo.dao.UserDao;
import com.travel.demo.entity.Category;
import com.travel.demo.entity.Route;
import com.travel.demo.entity.User;
import com.travel.demo.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteDao routeDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    public List<Route> findAll(User user) {

        return routeDao.findBySid(user.getUid());
    }

    /**
     * @return
     */
    public List<Route> findByRflag() {
//        routeDao.findAll().stream().filter(a->a.getRouteIntroduce()!=null).findFirst()
        routeDao.findByRflag("0").forEach(a -> {
            if ("1".equals(a.getIsThemeTour())) {
                a.setIsThemeTour("Yes");
                a.setCategory(categoryDao.findById(a.getCid()).get());
//                a.setSeller(categoryDao.findById(a.getCid()).get());

            } else {
                a.setIsThemeTour("No");
            }
        });
        return routeDao.findByRflag("0");
    }

    public boolean save(Route route) {
        try {
            routeDao.save(route);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String updateByRid(String rid,String rflag) {
        return routeDao.updateByRid(Integer.parseInt(rid),rflag)+"";
    }

    public Page<Route> pageQuery(String rname2, Integer currentPage2) {

        Pageable pageRequest = PageRequest.of(currentPage2, 6);
        return routeDao.findByRnameLike("%"+rname2+"%",pageRequest );
    }
}

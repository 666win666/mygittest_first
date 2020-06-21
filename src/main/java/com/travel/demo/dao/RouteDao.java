package com.travel.demo.dao;


import com.travel.demo.entity.Route;
import com.travel.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RouteDao extends JpaRepository<Route,Integer> {

    /**
     * 根据用户名查询用户信息
     * @param sid
     * @return
     */
    List<Route> findBySid(int sid);

    List<Route> findByRflag(String rflag);

    @Transactional
    @Modifying
    @Query(value = "update Route set rflag=:rflag where rid=:rid")
    int updateByRid(@Param("rid") int rid, @Param("rflag") String rflag);

    Page<Route> findByRnameLike(String rname2, Pageable pageRequest);
}

package com.travel.demo.dao;


import com.travel.demo.entity.Admin;
import com.travel.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    Admin findByUsername(String username);


    Admin findByCode(String code);

    Admin findByUsernameAndPassword(String username, String password);
}

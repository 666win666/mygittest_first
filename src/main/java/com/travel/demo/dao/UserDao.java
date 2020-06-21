package com.travel.demo.dao;


import com.travel.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);


    User findByCode(String code);

    User findByUsernameAndPassword(String username, String password);
}

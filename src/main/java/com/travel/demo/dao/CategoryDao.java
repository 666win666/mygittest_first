package com.travel.demo.dao;


import com.travel.demo.entity.Category;
import com.travel.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {


}

package com.travel.demo.controller;


import com.travel.demo.dao.CategoryDao;
import com.travel.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/findAll")
    public List<Category> login() {

        return categoryDao.findAll();
    }

}

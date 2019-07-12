package com.flyex.category.service;

import com.flyex.category.dao.CategoryDao;
import com.flyex.category.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> findAll(){
        return categoryDao.findAll();
    }
}

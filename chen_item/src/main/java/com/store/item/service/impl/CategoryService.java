package com.store.item.service.impl;

import com.store.item.dao.CategoryDao;
import com.store.item.pojo.Category;
import com.store.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long pid) {
        return categoryDao.findById(pid).get();
    }

    @Override
    public List<Category> findByParentId(Long pid) {
        return categoryDao.findByParentId(pid);
    }
}

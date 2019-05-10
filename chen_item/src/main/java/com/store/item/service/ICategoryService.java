package com.store.item.service;

import com.store.item.pojo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(Long pid);

    List<Category> findByParentId(Long pid);
}

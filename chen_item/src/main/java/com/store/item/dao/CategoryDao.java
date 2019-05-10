package com.store.item.dao;

import com.store.item.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Long> {

    List<Category> findByParentId(Long pid);
}

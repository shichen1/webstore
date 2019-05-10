package com.store.item.dao;

import com.store.item.pojo.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BrandDao extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {

    @Transactional
    @Modifying
    @Query(value = "insert into tb_category_brand (category_id, brand_id) values (?, ?)",nativeQuery = true)
    int saveCategoryBrand(Long cid, Long id);
}

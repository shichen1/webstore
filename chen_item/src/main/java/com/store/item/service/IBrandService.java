package com.store.item.service;

import com.store.item.pojo.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandService {

    Page<Brand> findBrandPage(Integer page, Integer rows, String key, Boolean desc);

    void saveBrand(Brand brand, List<Long> cids);
}

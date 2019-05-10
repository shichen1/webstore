package com.store.item.service.impl;

import com.store.item.dao.BrandDao;
import com.store.item.pojo.Brand;
import com.store.item.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandDao brandDao;

    /**
     * 动态条件构建
     */
    private Specification<Brand> createSpecification(String key) {
        return (Root<Brand> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (key != null && !"".equals(key)) {
                predicates.add(cb.like(root.get("name").as(String.class), "%" + key + "%"));
            }
            if (key != null && !"".equals(key)) {
                predicates.add(cb.equal(root.get("letter").as(String.class), key.toUpperCase()));
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public Page<Brand> findBrandPage(Integer page, Integer rows, String key, Boolean desc) {
        Sort sort = new Sort(desc ? Sort.Direction.DESC : Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page - 1, rows, sort);
        if (key != null && !"".equals(key)) {
            Specification<Brand> specification = createSpecification(key);
            return brandDao.findAll(specification, pageRequest);
        }
        return brandDao.findAll(pageRequest);
    }

    @Transactional
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        Brand brand1 = brandDao.save(brand);
        for (Long cid : cids) {
            brandDao.saveCategoryBrand(cid, brand1.getId());
        }
    }
}

package com.store.item.controller;

import com.store.item.pojo.Brand;
import com.store.item.service.IBrandService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping("/page/{page}/{rows}")
    public Result findBrandByPage(@PathVariable Integer page, @PathVariable Integer rows, @RequestParam(defaultValue = "",required = false) String key, @RequestParam(defaultValue = "false") Boolean desc) {
        Page<Brand> result = brandService.findBrandPage(page, rows, key, desc);
        if (result == null) {
            return new Result(false, StatusCode.ERROR, "查询错误");
        }
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Brand>(result.getTotalElements(), result.getContent()));
    }

    @PostMapping("/save")
    public Result saveBrand(@RequestBody Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return new Result(true, StatusCode.OK, "保存成功");
    }
}

package com.store.item.controller;

import com.store.item.pojo.Category;
import com.store.item.service.ICategoryService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public Result queryByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        List<Category> list = categoryService.findByParentId(pid);
        if (list == null || list.size() < 1) {
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}

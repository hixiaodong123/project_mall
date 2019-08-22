package com.cskaoyan.mall.controller.wxController.wxcatalog;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXCatalogController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("catalog/index")
    public BaseResponseModel catalogIndex() {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Map<String, Object> data = new HashMap<>();
        List<Category> categories = categoryService.queryAllParentCategory();
        Category currentCategory = categoryService.selectByPrimaryKey(categories.get(0).getId());
        List<Category> currentSubCategory = categoryService.selectByPid(categories.get(0).getId());
        data.put("categoryList", categories);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        baseResponseModel.setData(data);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }

    @RequestMapping("catalog/current")
    public BaseResponseModel current(int id) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Category currentCategory = categoryService.selectByPrimaryKey(id);
        List<Category> currentSubCategory = categoryService.selectByPid(currentCategory.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        baseResponseModel.setData(data);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }
}

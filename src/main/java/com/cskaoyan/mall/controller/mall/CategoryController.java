package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryList;
import com.cskaoyan.mall.bean.mall.L1Category;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("category/l1")
    public BaseResponseModel l1() {
        List<L1Category> l1 = categoryService.queryL1Category();
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setData(l1);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping("category/list")
    public BaseResponseModel list() {
        List<CategoryList> categories = categoryService.queryCategoryList();
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setData(categories);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }

    @RequestMapping("category/update")
    public BaseResponseModel update(@RequestBody CategoryList categoryList) {
        int update = categoryService.updateByPrimaryKeySelective(categoryList);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(update == 1) {
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
        }
        return baseResponseModel;
    }

    @RequestMapping("category/delete")
    public BaseResponseModel delete(@RequestBody CategoryList categoryList) {
        int i = categoryService.deleteByPrimaryKey(categoryList.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(i == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
        }
        return baseResponseModel;
    }

    @RequestMapping("category/create")
    public BaseResponseModel create(@RequestBody Category category) {
        int insert = categoryService.insert(category);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(insert == 1) {
            Category category1 = categoryService.selectCategoryByLevelAndKeywordsAndDescAndPid(category.getLevel(), category.getKeywords(),
                    category.getDesc(), category.getPid());
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
            baseResponseModel.setData(category1);
        }
        return baseResponseModel;
    }
}

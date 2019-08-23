package com.cskaoyan.mall.controller.wxcontroller.wxgoods;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.service.mall.CategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("wx")
public class WXGoodsController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    Set<Category> filter = new HashSet<>();

    String oldKeyword;

    @RequestMapping("goods/count")
    public BaseResponseModel count() {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        GoodsExample goodsExample = new GoodsExample();
        long l = goodsService.countByExample(goodsExample);
        Map data = new HashMap();
        data.put("goodsCount", l);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(data);
        return baseResponseModel;
    }

    @RequestMapping("goods/list")
    public BaseResponseModel goodsList(String keyword, int page, int size, String sort, String order, Integer categoryId) {
        PageHelper.startPage(page, size);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Map<String, Object> data = new HashMap<>();
        List<Goods> goodsList = goodsService.queryGoodsByKeywordOrId(keyword, sort, order, categoryId);
        if(categoryId == 0) {
            if(oldKeyword != keyword) {
                filter.clear();
            }
            Set<Integer> idSet = new HashSet<>();
            for (Goods goods : goodsList) {
                idSet.add(goods.getCategoryId());
            }
            List<Category> filterCategoryList = new ArrayList<>();
            for (int i : idSet) {
                Category category = categoryService.selectByPrimaryKey(i);
                filterCategoryList.add(category);
                filter.add(category);
            }
            data.put("filterCategoryList", filterCategoryList);
        }
        data.put("filterCategoryList", filter);
        data.put("goodsList", goodsList);
        baseResponseModel.setData(data);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }

    // 根据一个category的Id去查询该category的父类和兄弟类
    @RequestMapping("goods/category")
    public BaseResponseModel goodsCategory(int id) {
        Category currentCategory = categoryService.selectByPrimaryKey(id);
        List<Category> brotherCategory = categoryService.queryBrotherCategory(currentCategory.getPid());
        Category parentCategory = categoryService.queryParentCategory(currentCategory.getPid());
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", currentCategory);
        data.put("brotherCategory", brotherCategory);
        data.put("parentCategory", parentCategory);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(data);
        return baseResponseModel;
    }
}

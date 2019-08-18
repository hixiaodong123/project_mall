package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.CategoryList;
import com.cskaoyan.mall.bean.mall.L1Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.service.mall.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<L1Category> queryL1Category() {
        List<L1Category> l1 = categoryMapper.queryL1Category();
        return l1;
    }

    @Override
    public List<CategoryList> queryCategoryList() {
        List<CategoryList> categories = categoryMapper.queryCategoryList();
        return categories;
    }

    @Override
    public int updateByPrimaryKeySelective(CategoryList categoryList) {
        int i = categoryMapper.updateByPrimaryKeySelective(categoryList);
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = categoryMapper.deleteByPrimaryKey(id);
        return i;
    }


}

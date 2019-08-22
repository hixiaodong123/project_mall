package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
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

    @Override
    public int insert(Category category) {
        int insert = categoryMapper.insert(category);
        return insert;
    }

    @Override
    public Category selectCategoryByLevelAndKeywordsAndDescAndPid(String level, String keywords, String desc, Integer pid) {
        Category category = categoryMapper.selectCategoryByLevelAndKeywordsAndDescAndPid(level, keywords, desc, pid);
        return category;
    }

    @Override
    public Category selectByPrimaryKey(int id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public List<Category> selectByExample(CategoryExample categoryExample) {
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return categories;
    }

    @Override
    public List<Category> selectByPid(int pid) {
        List<Category> categories = categoryMapper.selectByPid(pid);
        return categories;
    }

    @Override
    public List<Category> queryBrotherCategory(Integer pid) {
        List<Category> categoryList = categoryMapper.selectByPid(pid);
        return categoryList;
    }

    @Override
    public Category queryParentCategory(Integer pid) {
        Category category = categoryMapper.selectByPrimaryKey(pid);
        return category;
    }

    @Override
    public List<Category> queryAllParentCategory() {
        List<Category> categoryList = categoryMapper.queryAllParentCategory();
        return categoryList;
    }
}

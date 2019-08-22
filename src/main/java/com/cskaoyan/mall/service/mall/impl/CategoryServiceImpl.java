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
    public List<Category> firstCategory() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(0).andDeletedEqualTo(false);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
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


}

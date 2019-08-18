package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.CategoryList;
import com.cskaoyan.mall.bean.L1Category;

import java.util.List;

public interface CategoryService {

    List<L1Category> queryL1Category();

    List<CategoryList> queryCategoryList();

    int updateByPrimaryKeySelective(CategoryList categoryList);

    int deleteByPrimaryKey(Integer id);
}

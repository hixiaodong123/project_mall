package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import com.cskaoyan.mall.bean.CategoryList;
import com.cskaoyan.mall.bean.mall.L1Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {

    List<L1Category> queryL1Category();

    List<Category> firstCategory();

    List<CategoryList> queryCategoryList();

    int updateByPrimaryKeySelective(CategoryList categoryList);

    int deleteByPrimaryKey(Integer id);

    int insert(Category category);

    Category selectCategoryByLevelAndKeywordsAndDescAndPid(@Param("level") String level, @Param("keywords") String keywords,
                                                           @Param("desc") String desc, @Param("pid") Integer pid);

    Category selectByPrimaryKey(int id);

    List<Category> selectByExample(CategoryExample categoryExample);

    List<Category> selectByPid (int pid);

    List<Category> queryBrotherCategory(Integer pid);

    Category queryParentCategory(Integer pid);

    List<Category> queryAllParentCategory();

    Category selectCategoryById(Integer integer);
}

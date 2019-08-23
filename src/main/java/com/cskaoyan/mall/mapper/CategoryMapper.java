package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import java.util.List;

import com.cskaoyan.mall.bean.CategoryList;
import com.cskaoyan.mall.bean.mall.L1Category;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKeySelective(CategoryList record);

    int updateByPrimaryKey(Category record);

    List<L1Category> queryL1Category();

    List<CategoryList> queryCategoryList();

    List<Category> selectAllCat(@Param("level") String level);

    List<Category> selectByPid(@Param("pid") int pid);

    Integer selectFirstLevelBySortOrder(@Param("sortOrder")Byte sortOrder);

    Category selectCategoryByLevelAndKeywordsAndDescAndPid(String level, String keywords, String desc, Integer pid);

    List<Category> queryAllParentCategory();

    List<Category> queryChildCategory(@Param("id") int id);
}
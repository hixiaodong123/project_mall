package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> queryBrandList(@Param("id") Integer id, @Param("name") String name, @Param("sort") String sort, @Param ("order")String order);

    Brand selectBrandByDescAndNameAndFloorPriceAndPicUrl(@Param("desc") String desc, @Param("name") String name, @Param("floorPrice") BigDecimal floorPrice, @Param("picUrl") String picUrl);
}
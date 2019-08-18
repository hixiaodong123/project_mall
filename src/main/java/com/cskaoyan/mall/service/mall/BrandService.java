package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Brand;

import java.math.BigDecimal;
import java.util.List;

public interface BrandService {
    List<Brand> queryBrandList(Integer id, String name, String sort, String order);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Brand brand);

    Brand selectByPrimaryKey(Integer id);

    int insert(Brand brand);

    Brand selectBrandByDescAndNameAndFloorPriceAndPicUrl(String desc, String name, BigDecimal floorPrice, String picUrl);
}

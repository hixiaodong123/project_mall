package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.service.mall.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Brand> queryBrandList(Integer id, String name, String sort, String order) {
        List<Brand> brands = brandMapper.queryBrandList(id, name, sort, order);
        return brands;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int delete = brandMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public int updateByPrimaryKey(Brand brand) {
        int update = brandMapper.updateByPrimaryKey(brand);
        return update;
    }

    @Override
    public Brand selectByPrimaryKey(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public int insert(Brand brand) {
        int insert = brandMapper.insert(brand);
        return insert;
    }

    @Override
    public Brand selectBrandByDescAndNameAndFloorPriceAndPicUrl(String desc, String name, BigDecimal floorPrice, String picUrl) {
        return null;
    }
}

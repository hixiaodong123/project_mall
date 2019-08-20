package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.page.PageBean;
import com.cskaoyan.mall.service.mall.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class BrandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("brand/list")
    public BaseResponseModel list(int page, int limit, Integer id, String name, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Brand> brands = brandService.queryBrandList(id, name, sort, order);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        PageBean<Brand> data = new PageBean<>(brands, pageInfo.getTotal());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setData(data);
        return baseResponseModel;
    }

    @RequestMapping("brand/update")
    public BaseResponseModel update(@RequestBody Brand brand) {
        int update = brandService.updateByPrimaryKey(brand);
        Brand brand1 = brandService.selectByPrimaryKey(brand.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if (update == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setData(brand1);
        }
        return baseResponseModel;
    }


    @RequestMapping("brand/delete")
    public BaseResponseModel delete(@RequestBody Brand brand) {
        int delete = brandService.deleteByPrimaryKey(brand.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(delete == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
        }
        return baseResponseModel;
    }

    @RequestMapping("brand/create")
    public BaseResponseModel create(@RequestBody Brand brand) {
        int insert = brandService.insert(brand);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(insert == 1) {
            Brand brand1 = brandService.selectBrandByDescAndNameAndFloorPriceAndPicUrl(brand.getDesc(), brand.getName(),
                    brand.getFloorPrice(), brand.getPicUrl());
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
            baseResponseModel.setData(brand1);
        }
        return baseResponseModel;
    }
}

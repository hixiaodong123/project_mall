package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponExample;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.popularize.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public long countByExample(CouponExample example) {
        return couponMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CouponExample example) {
        return couponMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return couponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Coupon record) {
        return couponMapper.insert(record);
    }

    @Override
    public int insertSelective(Coupon record) {
        return couponMapper.insertSelective(record);
    }

    @Override
    public List<Coupon> selectByExample(CouponExample example) {
        return couponMapper.selectByExample(example);
    }

    @Override
    public List<Coupon> selectAll(String name, Integer type, Integer status, String sort, String order) {
        return couponMapper.selectAll(name,type,status,sort,order);
    }

    @Override
    public Coupon selectByPrimaryKey(Integer id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Coupon record, CouponExample example) {
        return updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Coupon record, CouponExample example) {
        return couponMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Coupon record) {
        return couponMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Coupon record) {
        return couponMapper.updateByPrimaryKey(record);
    }
}

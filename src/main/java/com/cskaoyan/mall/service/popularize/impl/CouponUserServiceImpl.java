package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.CouponUserExample;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.service.popularize.CouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponUserServiceImpl implements CouponUserService {

    @Autowired
    CouponUserMapper couponUserMapper;

    @Override
    public long countByExample(CouponUserExample example) {
        return couponUserMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CouponUserExample example) {
        return couponUserMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return couponUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CouponUser record) {
        return couponUserMapper.insert(record);
    }

    @Override
    public int insertSelective(CouponUser record) {
        return couponUserMapper.insertSelective(record);
    }

    @Override
    public List<CouponUser> selectByExample(CouponUserExample example) {
        return couponUserMapper.selectByExample(example);
    }

    @Override
    public List<CouponUser> selectByConditions(Integer couponId, Integer userId, Integer status, String sort, String order) {
        return couponUserMapper.selectByConditions(couponId, userId, status, sort, order);
    }

    @Override
    public CouponUser selectByPrimaryKey(Integer id) {
        return couponUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(CouponUser record, CouponUserExample example) {
        return couponUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(CouponUser record, CouponUserExample example) {
        return couponUserMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(CouponUser record) {
        return couponUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CouponUser record) {
        return couponUserMapper.updateByPrimaryKey(record);
    }
}

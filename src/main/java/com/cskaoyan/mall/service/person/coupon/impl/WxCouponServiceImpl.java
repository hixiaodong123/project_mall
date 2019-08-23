package com.cskaoyan.mall.service.person.coupon.impl;


import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponExample;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.CouponUserExample;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.service.person.coupon.WxCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * @description: 优惠券业务实现类
 * @author: Lime
 * @create: 2019-08-22 10:39
 **/

@Service
public class WxCouponServiceImpl implements WxCouponService
{

    private final CouponUserMapper couponUserMapper;
    private final CouponMapper couponMapper;

    @Autowired
    public WxCouponServiceImpl(CouponMapper couponMapper, CouponUserMapper couponUserMapper)
    {
        this.couponMapper = couponMapper;
        this.couponUserMapper = couponUserMapper;
    }

    @Override
    public List<CouponUser> findByStatus(Short status)
    {
        CouponUserExample couponExample = new CouponUserExample();
        couponExample.createCriteria().andStatusEqualTo(status).andDeletedEqualTo(false);
        return couponUserMapper.selectByExample(couponExample);
    }

    @Override
    public long findCount()
    {

        return couponUserMapper.countByExample(null);
    }

    @Override
    public Coupon selectById(Integer couponId)
    {
        return couponMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public long CountCouponById(Integer couponId)
    {
        CouponUserExample couponuserExample = new CouponUserExample();
        couponuserExample.createCriteria().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (couponUserMapper.countByExample(couponuserExample));
    }

    @Override
    public int countByUseIdAndCoupId(Integer userId, Integer couponId)
    {
        CouponUserExample couponuserExample = new CouponUserExample();
        couponuserExample.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int) couponUserMapper.countByExample(couponuserExample);
    }

    @Override
    public void add(CouponUser couponuser)
    {
        couponuser.setAddTime(new Date());
        couponuser.setUpdateTime(new Date());
        couponUserMapper.insert(couponuser);
    }

    @Override
    public Coupon findByCode(String code)
    {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andCodeEqualTo(code);
        List<Coupon> list = couponMapper.selectByExample(couponExample);
        if (list.size() > 0)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }

    }
}

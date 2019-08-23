package com.cskaoyan.mall.service.person.coupon.impl;


import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponExample;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.person.coupon.WxCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 优惠券业务实现类
 * @author: Lime
 * @create: 2019-08-22 10:39
 **/

@Service
public class WxCouponServiceImpl implements WxCouponService
{

    private final CouponMapper couponMapper;

    @Autowired
    public WxCouponServiceImpl(CouponMapper couponMapper)
    {
        this.couponMapper = couponMapper;
    }

    @Override
    public List<Coupon> findByStatus(Short status)
    {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andStatusEqualTo((status));
        return couponMapper.selectByExample(couponExample);
    }

    @Override
    public long findCount()
    {

        return couponMapper.countByExample(null);
    }
}

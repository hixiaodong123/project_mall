package com.cskaoyan.mall.service.person.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;

import java.util.List;

/**
 * @description: 优惠券相关
 * @author: Lime
 * @create: 2019-08-22 10:38
 **/

public interface WxCouponService
{
    List<CouponUser> findByStatus(Short status);

    long findCount();

    Coupon selectById(Integer couponId);

    long CountCouponById(Integer couponId);

    int countByUseIdAndCoupId(Integer userId, Integer couponId);

    void add(CouponUser couponuser);

    Coupon findByCode(String code);
}

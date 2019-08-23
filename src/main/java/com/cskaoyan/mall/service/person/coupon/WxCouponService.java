package com.cskaoyan.mall.service.person.coupon;

import com.cskaoyan.mall.bean.Coupon;

import java.util.List;

/**
 * @description: 优惠券相关
 * @author: Lime
 * @create: 2019-08-22 10:38
 **/

public interface WxCouponService
{
    List<Coupon> findByStatus(Short status);

    long findCount();
}

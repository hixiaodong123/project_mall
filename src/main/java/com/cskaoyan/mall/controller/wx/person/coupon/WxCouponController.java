package com.cskaoyan.mall.controller.wx.person.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.person.coupon.WxCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 优惠券相关业务
 * @author: Lime
 * @create: 2019-08-22 10:24
 **/

@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController
{

    private final WxCouponService wxCouponService;

    @Autowired
    public WxCouponController(WxCouponService wxCouponService)
    {
        this.wxCouponService = wxCouponService;
    }

    @GetMapping("/mylist")
    public BaseResponseModel myList(Short status)
    {
        BaseResponseModel<Map<String,Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        //初始化data的Map
        Map<String,Object> map  = new HashMap<>();
        //装入map集合的count元素
        long count = wxCouponService.findCount();
        map.put("count",count);
        //装入map集合的data元素
        List<Coupon> data = wxCouponService.findByStatus(status);
        map.put("data",data);

        //将map传入
        baseResponseModel.setData(map);
        return baseResponseModel;
    }

}

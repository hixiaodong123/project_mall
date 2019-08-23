package com.cskaoyan.mall.controller.webcontroller.popularize;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.CouponService;
import com.cskaoyan.mall.service.popularize.CouponUserService;
import com.cskaoyan.mall.utils.popularize.PopularizeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    CouponUserService couponUserService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public BaseResponseModel<Map<String,Object>> list(int page, int limit, String name, Integer type,Integer status, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Coupon> coupons = couponService.selectAll(name, type,status, sort, order);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(coupons);
        long total = couponPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("items", coupons);
        map.put("total", total);
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setData(map);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public BaseResponseModel<Coupon> creat(@RequestBody Coupon coupon) {
        coupon.setAddTime(new Date());
        coupon.setUpdateTime(new Date());
        coupon.setDeleted(false);
        int insert = couponService.insert(coupon);
        BaseResponseModel<Coupon> couponBaseResponseModel = PopularizeUtils.respValue(coupon, insert);
        return couponBaseResponseModel;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResponseModel<Coupon> update(@RequestBody Coupon coupon) {
        coupon.setUpdateTime(new Date());
        int update = couponService.updateByPrimaryKeySelective(coupon);
        BaseResponseModel<Coupon> couponBaseResponseModel = PopularizeUtils.respValue(coupon, update);
        return couponBaseResponseModel;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestBody Coupon coupon) {
        coupon.setDeleted(true);
        int update = couponService.updateByPrimaryKeySelective(coupon);
        BaseResponseModel<Coupon> couponBaseResponseModel = PopularizeUtils.respValue(coupon, update);
        HashMap<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }

    @RequestMapping("read")
    public BaseResponseModel<Coupon> read(String id) {
        BaseResponseModel<Coupon> couponBaseResponseModel = new BaseResponseModel<>();
        int newId = Integer.parseInt(id);
        Coupon coupon = couponService.selectByPrimaryKey(newId);
        couponBaseResponseModel.setData(coupon);
        couponBaseResponseModel.setErrmsg("成功");
        couponBaseResponseModel.setErrno(0);
        return couponBaseResponseModel;
    }

    @RequestMapping(value = "listuser", method = RequestMethod.GET)
    public BaseResponseModel<Map<String,Object>> listuser(int page, int limit,Integer couponId,Integer userId,Integer status,String sort, String order) {
        PageHelper.startPage(page, limit);
        List<CouponUser> couponUsers = couponUserService.selectByConditions(couponId,userId,status,sort,order);
        PageInfo<CouponUser> couponUserPageInfo = new PageInfo<>(couponUsers);
        long total = couponUserPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("items", couponUsers);
        map.put("total", total);
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setData(map);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }
}

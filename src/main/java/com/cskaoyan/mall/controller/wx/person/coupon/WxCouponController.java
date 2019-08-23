package com.cskaoyan.mall.controller.wx.person.coupon;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.utils.wx.TimeUtils;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.person.coupon.WxCouponService;
import com.cskaoyan.mall.utils.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 优惠券相关业务
 * @author: Lime
 * @create: 2019-08-22 10:24
 **/

@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController
{

    private final CouponMapper couponMapper;

    private final WxCouponService wxCouponService;

    @Autowired
    public WxCouponController(WxCouponService wxCouponService, CouponMapper couponMapper)
    {
        this.wxCouponService = wxCouponService;
        this.couponMapper = couponMapper;
    }

    @GetMapping("/mylist")
    public BaseResponseModel myList(Short status, Integer page, Integer size)
    {
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        //初始化data的Map
        Map<String, Object> map = new HashMap<>();
        //装入map集合的count元素
        long count = wxCouponService.findCount();
        map.put("count", count);
        //装入map集合的data元素
        PageHelper.startPage(page, size);
        List<CouponUser> couponUsers = wxCouponService.findByStatus(status);
        List<Coupon> data = new ArrayList<>();
        for (CouponUser couponUser : couponUsers)
        {
            Integer couponId = couponUser.getCouponId();
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            coupon.setStartTime(couponUser.getStartTime());
            coupon.setEndTime(couponUser.getEndTime());
            data.add(coupon);
        }
        map.put("data", data);

        //将map传入
        baseResponseModel.setData(map);
        return baseResponseModel;
    }


    /*优惠卷接收相关*/
    @PostMapping("/receive")
    public BaseResponseModel receive(@RequestBody JSONObject JcouponId, HttpServletRequest request)
    {

        Subject subject = SecurityUtils.getSubject();
        Integer couponId = JcouponId.getInteger("couponId");
        //获取userId
        String token = request.getHeader("X-Litemall-Token");
        Integer userId = null;
        if (token != null && !"".equals(token))
        {
            userId = UserTokenManager.getUserId(token);
        }
        BaseResponseModel<Coupon> baseRespModel = new BaseResponseModel<>();
        Coupon coupon = wxCouponService.selectById(couponId);

        //优惠券领取完
        Integer total = coupon.getTotal();
        //已经领取的这个ID得券
        Long totalCoupon = wxCouponService.CountCouponById(couponId);
        if ((total != 0) && totalCoupon >= total)
        {
            baseRespModel.setErrno(700);
            baseRespModel.setErrmsg("优惠券已领完");
            return baseRespModel;
        }
        //优惠券已领取过,如果limit为0不限制，1则限领一张
        int limit = coupon.getLimit().intValue();
        int number = wxCouponService.countByUseIdAndCoupId(userId, couponId);
        if (limit != 0 && number >= limit)
        {
            baseRespModel.setErrno(740);
            baseRespModel.setErrmsg("优惠券已经领取过");
            return baseRespModel;
        }
        //优惠券已过期
        CouponUser couponuser = new CouponUser();
        couponuser.setStatus(((short) 0));
        couponuser.setStartTime(new Date());
        couponuser.setEndTime(TimeUtils.addTime(coupon.getDays(), new Date()));
        couponuser.setDeleted(false);
        Date endTime = coupon.getEndTime();
        Date now = new Date();
        if (endTime.getTime() - now.getTime() < 0)
        {
            baseRespModel.setErrno(701);
            baseRespModel.setErrmsg("优惠券已经过期");
            return baseRespModel;
        }
        //优惠券添加成功
        couponuser.setCouponId(couponId);
        couponuser.setUserId(userId);
        wxCouponService.add(couponuser);
        baseRespModel.setErrmsg("领券成功");
        baseRespModel.setErrno(0);
        return baseRespModel;
    }

    //兑换码
    @PostMapping("/exchange")
    public BaseResponseModel exchange(@RequestBody JSONObject JcouponId, HttpServletRequest request)
    {

        //获取code码和用户userId
        Subject subject = SecurityUtils.getSubject();
        String code = JcouponId.getString("code");
        //获取userId
        String token = request.getHeader("X-Litemall-Token");
        Integer userId = null;
        if (token != null && !"".equals(token))
        {
            userId = UserTokenManager.getUserId(token);
        }


        BaseResponseModel<Coupon> baseRespModel = new BaseResponseModel<>();

        //查询兑换码是否存在
        Coupon coupon = wxCouponService.findByCode(code);
        if (coupon == null)
        {
            //无此兑换码
            baseRespModel.setErrmsg("优惠券码错误");
            baseRespModel.setErrno(742);
            return baseRespModel;
        }
        else
        {
            //兑换码存在

            //优惠券领取完
            Integer total = coupon.getTotal();
            Long totalCoupon = wxCouponService.CountCouponById(coupon.getId());
            //已经领取的这个ID得券
            if ((total != 0) && totalCoupon >= total)
            {
                baseRespModel.setErrno(700);
                baseRespModel.setErrmsg("优惠券已领完");
                return baseRespModel;
            }
            //优惠券已领取过,如果limit为0不限制，1则限领一张
            int limit = coupon.getLimit().intValue();
            int number = wxCouponService.countByUseIdAndCoupId(userId, coupon.getId());
            if (limit != 0 && number >= limit)
            {
                baseRespModel.setErrno(740);
                baseRespModel.setErrmsg("优惠券已经领取过");
                return baseRespModel;
            }
            //优惠券已过期
            CouponUser couponuser = new CouponUser();
            couponuser.setStatus(((short) 0));
            couponuser.setStartTime(new Date());
            couponuser.setEndTime(TimeUtils.addTime(coupon.getDays(), new Date()));
            couponuser.setDeleted(false);
            Date endTime = coupon.getEndTime();
            Date now = new Date();
            if (endTime.getTime() - now.getTime() < 0)
            {
                baseRespModel.setErrno(701);
                baseRespModel.setErrmsg("优惠券已经过期");
                return baseRespModel;
            }
            //优惠券添加成功
            couponuser.setCouponId(coupon.getId());
            couponuser.setUserId(userId);
            wxCouponService.add(couponuser);
            baseRespModel.setErrmsg("领券成功");
            baseRespModel.setErrno(0);
            return baseRespModel;


        }


    }

}

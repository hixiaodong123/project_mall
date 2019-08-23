package com.cskaoyan.mall.controller.wx.cart;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.bean_for_wx_car.OrderBeanForCat;
import com.cskaoyan.mall.bean.bean_for_wx_car.RecvBean;

import com.cskaoyan.mall.service.mall.OrderService;
import com.cskaoyan.mall.service.mall.RegionService;
import com.cskaoyan.mall.service.popularize.CouponService;
import com.cskaoyan.mall.service.user.AddressService;
import com.cskaoyan.mall.service.wx_service.cart.CartService;
import com.cskaoyan.mall.service.wx_service.cart.GoodsProductService;
import com.cskaoyan.mall.utils.wx_util.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class CarController {
    @Autowired
    CartService cartService;
    @Autowired
    AddressService addressService;
    @Autowired
    RegionService regionService;
    @Autowired
    CouponService couponService;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsProductService goodsProductService;

    Integer userId;
    @RequestMapping("/cart/index")
    public BaseResponseModel<Map<String, Object>> cartList(HttpServletRequest request) {
        //前端写了一个token放在请求头中
        //*************************
        //获得请求头
        String tokenKey = request.getHeader("X-Litemall-Token");
        userId = UserTokenManager.getUserId(tokenKey);
        BaseResponseModel<Map<String, Object>> mapBaseResponseModel = cartService.indexListService(userId);
        return mapBaseResponseModel;
    }

    @RequestMapping(value = "/cart/update", method = RequestMethod.POST)
    public BaseResponseModel update(@RequestBody Cart cart) {
        int i = goodsProductService.queryGoodsProductNumByGoodsId(cart.getGoodsId());
        short number = cart.getNumber();
        if (number <= i) {
            int update = cartService.updateByPrimaryKeySelective(cart);
            BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
            return baseResponseModel;
        } else {
            short j = (short)i;
            cart.setNumber(j);
            int update = cartService.updateByPrimaryKeySelective(cart);
            BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
            baseResponseModel.setErrmsg("失败");
            baseResponseModel.setErrno(724);
            return baseResponseModel;
        }
    }

    @RequestMapping(value = "/cart/delete", method = RequestMethod.POST)
    public BaseResponseModel<Map<String,Object>> delete(@RequestBody RecvBean recvBean) {
        int[] productIds = recvBean.getProductIds();
        for (int productId : productIds) {
            int update = cartService.updateByProductIdForDelete(productId);
        }
        BaseResponseModel<Map<String, Object>> mapBaseResponseModel = cartService.indexListService(userId);
        return mapBaseResponseModel;
    }

    @RequestMapping(value = "/cart/checked", method = RequestMethod.POST)
    public BaseResponseModel<Map<String,Object>> checked(@RequestBody RecvBean recvBean) {

        int[] productIds = recvBean.getProductIds();
        for (int productId : productIds) {
            int update = cartService.updateByProductIdForChecked(productId,recvBean.getIsChecked());
        }
        BaseResponseModel<Map<String, Object>> mapBaseResponseModel = cartService.indexListService(userId);
        return mapBaseResponseModel;
    }

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
    public BaseResponseModel checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        BaseResponseModel baseResponseModel = cartService.checkoutRespValue(cartId, addressId, couponId, grouponRulesId,userId);
        return baseResponseModel;
    }

    @RequestMapping(value = "/address/list", method = RequestMethod.GET)
    public BaseResponseModel addressList() {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        List<Address> addresses = addressService.selectAddressByUserId(userId);
        baseResponseModel.setData(addresses);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping(value = "/address/delete", method = RequestMethod.POST)
    public BaseResponseModel delete(@RequestBody Address address) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        int update = addressService.updateByAddressIdForDelete(address.getId());
        if (update == 1) {
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
        } else {
            baseResponseModel.setErrmsg("失败");
            baseResponseModel.setErrno(1);
        }
        return baseResponseModel;
    }

    @RequestMapping(value = "/region/list", method = RequestMethod.GET)
    public BaseResponseModel regionList(Integer pid) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        List<Region> regions = regionService.selectRegionByPid(pid);
        baseResponseModel.setData(regions);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping(value = "/address/save", method = RequestMethod.POST)
    public BaseResponseModel addressSave(@RequestBody Address address) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        address.setId(null);
        address.setUserId(userId);
        address.setAddTime(new Date());
        address.setUpdateTime(new Date());
        address.setDeleted(false);
        int insert = addressService.insert(address);
        long l = addressService.countByExample(new AddressExample());
        baseResponseModel.setData(l + 1);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping(value = "/coupon/selectlist", method = RequestMethod.GET)
    public BaseResponseModel couponSelectList(Integer cartId, Integer grouponRulesId) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        List<Coupon> coupons = couponService.selectByExample(new CouponExample());
        if (coupons != null) {
            baseResponseModel.setData(coupons);
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
        } else {
            baseResponseModel.setData(null);
            baseResponseModel.setErrmsg("失败");
            baseResponseModel.setErrno(1);
        }
        return baseResponseModel;
    }

    @RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    public BaseResponseModel orderSubmit(@RequestBody OrderBeanForCat orderBeanForCat) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();


        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", 123);
        baseResponseModel.setData(map);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);

        return baseResponseModel;
    }

    @RequestMapping(value = "/order/prepay", method = RequestMethod.POST)
    public BaseResponseModel orderPrepay(@RequestBody OrderBeanForCat orderBeanForCat) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setErrmsg("订单不能支付");
        baseResponseModel.setErrno(724);
        return baseResponseModel;
    }
}

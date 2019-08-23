package com.cskaoyan.mall.service.wx_service.cart.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.service.popularize.CouponService;
import com.cskaoyan.mall.service.popularize.GrouponRulesService;
import com.cskaoyan.mall.service.wx_service.cart.CartService;
import com.cskaoyan.mall.utils.wx_util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponService couponService;
    @Autowired
    GrouponRulesService grouponRulesService;

    @Override
    public long countByExample(CartExample example) {
        return cartMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CartExample example) {
        return cartMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Cart record) {
        return 0;
    }

    @Override
    public int insertSelective(Cart record) {
        return 0;
    }

    @Override
    public List<Cart> selectByExample(CartExample example) {
        return cartMapper.selectByExample(example);
    }

    @Override
    public Cart selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Cart record, CartExample example) {
        return updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Cart record, CartExample example) {
        return updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Cart record) {
        return cartMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Cart record) {
        return 0;
    }



    @Override
    public List<Cart> selectByUserId(Integer userId) {
        return cartMapper.selectByUserId(userId);
    }

    @Override
    public int updateByProductIdForDelete(Integer productId) {
        return cartMapper.updateByProductIdForDelete(productId);
    }

    @Override
    public int updateByProductIdForChecked(Integer productId, Integer checked) {
        return cartMapper.updateByProductIdForChecked(productId, checked);
    }

    @Override
    public List<Cart> selectByUserIdAndChecked(Integer userId) {
        return cartMapper.selectByUserIdAndChecked(userId);
    }

    @Override
    public BaseResponseModel<Map<String,Object>> indexListService(int userId) {
        int goodsCount = 0;
        int checkedGoodsCount = 0;
        Short number = 0;
        Short number1 = 0;
        List<Cart> carts = cartMapper.selectByUserId(userId);
        BigDecimal goodsAmount = CartUtil.getGoodsAmount(carts);
        BigDecimal checkedGoodsAmount = CartUtil.getCheckedGoodsAmount(carts);
        for (Cart cart : carts) {
            goodsCount += number;
            if (cart.getChecked()) {
                checkedGoodsCount += number1;
            }
        }
        BaseResponseModel<Map<String, Object>> mapBaseResponseModel = CartUtil.respValue(carts, checkedGoodsAmount, checkedGoodsCount, goodsAmount, goodsCount);

        return mapBaseResponseModel;
    }

    @Override
    public BaseResponseModel checkoutRespValue(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId,Integer userId) {
        BaseResponseModel<Object> baseResponseModel = new BaseResponseModel<>();
        Map<String, Object> map = new HashMap<>();
        List<Cart> carts = cartMapper.selectByUserIdAndChecked(userId);
        BigDecimal checkedGoodsAmount = CartUtil.getCheckedGoodsAmount(carts);
        BigDecimal goodsAmount = CartUtil.getGoodsAmount(carts);
        Coupon coupon = null;
        if (couponId != 0) {
            coupon = couponService.selectByPrimaryKey(couponId);
            if (checkedGoodsAmount.compareTo(coupon.getMin()) > -1) {
                BigDecimal discount = coupon.getDiscount();
                checkedGoodsAmount = checkedGoodsAmount.subtract(discount);
            }
        }
        Address address = addressMapper.selectByPrimaryKey(addressId);
        map.put("actualPrice", checkedGoodsAmount);
        map.put("addressId", addressId);
        map.put("availableCouponLength", couponService.countByExample(new CouponExample()));
        map.put("checkedAddress",address );
        map.put("checkedGoodsList",carts );
        map.put("couponId",couponId);
        if (coupon != null) {
            map.put("couponPrice", coupon.getDiscount());
        } else {
            map.put("couponPrice",0);
        }
        map.put("freightPrice", 0);
        map.put("goodsTotalPrice", goodsAmount);
        map.put("grouponPrice", 0);
        map.put("grouponRulesId", grouponRulesId);
        map.put("orderTotalPrice", checkedGoodsAmount);

        baseResponseModel.setData(map);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

}

package com.cskaoyan.mall.service.cart.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.bean_for_wx_car.OrderBeanForCat;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.cart.CartService;
import com.cskaoyan.mall.utils.wx.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

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
        return cartMapper.insert(record);
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
    public List<Cart> selectByUserIdAndChecked(Integer userId,Integer cartId) {
        return cartMapper.selectByUserIdAndChecked(userId,cartId);
    }

    @Override
    public Order addOrder(OrderBeanForCat orderBeanForCat) {
        Order order = new Order();
        // Coupon coupon = couponMapper.selectByPrimaryKey(orderBeanForCat.getCouponId());
        Coupon coupon = null;
        Integer couponId = orderBeanForCat.getCouponId();
        if (couponId != null && couponId != 0) {
            coupon = couponMapper.selectByPrimaryKey(couponId);
        }
        Address address = addressMapper.selectByPrimaryKey(orderBeanForCat.getAddressId());
        List<Cart> carts = cartMapper.selectByUserIdAndChecked(address.getUserId(),null);
        order.setUserId(address.getUserId());
        order.setOrderSn(UUID.randomUUID().toString());
        int random = (int) (Math.random()*9);
        short status = 0;
        switch (random) {
            case 0:
                status = 101;
            case 1:
                status = 102;
            case 2:
                status = 103;
            case 3:
                status = 201;
            case 4:
                status = 202;
            case 5:
                status = 203;
            case 6:
                status = 301;
            case 7:
                status = 401;
            break;
        }
        order.setOrderStatus(status);
        order.setConsignee(address.getName());
        order.setMobile(address.getMobile());
        order.setAddress(address.getAddress());
        order.setMessage(orderBeanForCat.getMessage());
        order.setGoodsPrice(CartUtil.getGoodsAmount(carts));
        order.setFreightPrice(new BigDecimal(0));
        //order.setCouponPrice(coupon.getDiscount());
        if (coupon != null) {
            order.setCouponPrice(coupon.getDiscount());
        } else {
            order.setCouponPrice(new BigDecimal(0));
        }
        order.setIntegralPrice(new BigDecimal(0));
        order.setGrouponPrice(new BigDecimal(0));
        order.setOrderPrice(order.getGoodsPrice().add(order.getFreightPrice()).subtract(order.getCouponPrice()));
        order.setActualPrice(order.getOrderPrice().subtract(order.getIntegralPrice()));
        order.setPayId(UUID.randomUUID().toString());
        order.setAddTime(new Date());
        order.setShipSn(UUID.randomUUID().toString());
        order.setShipChannel("王道快递");
        order.setShipTime(new Date());

        /*其他几个属性未赋值*/

        order.setDeleted(false);

        int insert = orderMapper.insert(order);
        Order order1 = orderMapper.selectOrderByOrderSn(order.getOrderSn());

        /*提交订单后，购物车条目即删除*/
        for (Cart cart : carts) {
            int update = cartMapper.updateByProductIdForDelete(cart.getProductId());
        }
        return order1;
    }




    /* 加入购物车 */
    @Override
    public int addCart(Cart cart,Integer userId) {
        Cart cart1 = selectByUserIdAndProductId(cart.getProductId(), userId);
        if (cart1 != null) {
            short number1 = cart.getNumber();
            Short number2 = cart1.getNumber();
            short number = (short) (number1 + number2);
            cart1.setNumber(number);
            int update = updateByPrimaryKeySelective(cart1);
            return update;
        } else {
            Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(cart.getProductId());
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName(goods.getName());
            cart.setPrice(goodsProduct.getPrice());
            String[] specifications = goodsProduct.getSpecifications();
            cart.setSpecifications(specifications[0]);
            cart.setChecked(true);
            cart.setPicUrl(goodsProduct.getUrl());
            cart.setAddTime(new Date());
            cart.setUpdateTime(new Date());
            cart.setDeleted(false);
            cart.setUserId(userId);
            int insert = insert(cart);
            return insert;
        }
    }


    @Override
    public Cart selectByUserIdAndProductId(Integer productId, Integer userId) {
        return cartMapper.selectByUserIdAndProductId(productId, userId);
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
        List<Cart> carts = cartMapper.selectByUserIdAndChecked(userId,cartId);
        BigDecimal checkedGoodsAmount = CartUtil.getCheckedGoodsAmount(carts);
        BigDecimal goodsAmount = CartUtil.getGoodsAmount(carts);
        Coupon coupon = null;
        if (couponId != 0) {
            coupon = couponMapper.selectByPrimaryKey(couponId);
            if (checkedGoodsAmount.compareTo(coupon.getMin()) > -1) {
                BigDecimal discount = coupon.getDiscount();
                checkedGoodsAmount = checkedGoodsAmount.subtract(discount);
            }
        }
        Address address = addressMapper.selectByPrimaryKey(addressId);
        map.put("actualPrice", checkedGoodsAmount);
        map.put("addressId", addressId);
        map.put("availableCouponLength", couponMapper.countByExample(new CouponExample()));
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

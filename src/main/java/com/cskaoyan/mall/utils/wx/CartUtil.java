package com.cskaoyan.mall.utils.wx;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.base.BaseResponseModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartUtil {

    public static <T> BaseResponseModel<Map<String, Object>> respValue(List<T> list, Object obj1, Object obj2, Object obj3, Object obj4) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("checkedGoodsAmount", obj1);
        map.put("checkedGoodsCount", obj2);
        map.put("GoodsAmount", obj3);
        map.put("GoodsCount", obj4);

        Map<String, Object> map01 = new HashMap<>();
        map01.put("cartList", list);
        map01.put("cartTotal", map);

        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setData(map01);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    public static<T> BigDecimal getGoodsAmount(List<Cart> list) {
        int goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0);
        Short number = 0;
        BigDecimal singleAmount = new BigDecimal(0);
        for (Cart cart : list) {
            number = cart.getNumber();
            goodsCount += number;
            singleAmount = (cart.getPrice()).multiply(new BigDecimal(number));
            goodsAmount = goodsAmount.add(singleAmount);
        }
        return goodsAmount;
    }

    public static<T> BigDecimal getCheckedGoodsAmount(List<Cart> list) {
        int checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0);
        Short number1 = 0;
        BigDecimal singleAmount1 = new BigDecimal(0);
        for (Cart cart : list) {
            if (cart.getChecked()) {
                number1 = cart.getNumber();
                checkedGoodsCount += number1;
                singleAmount1 = (cart.getPrice()).multiply(new BigDecimal(number1));
                checkedGoodsAmount =checkedGoodsAmount.add(singleAmount1);
            }
        }
        return checkedGoodsAmount;
    }
}

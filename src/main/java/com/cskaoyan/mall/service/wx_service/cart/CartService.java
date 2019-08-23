package com.cskaoyan.mall.service.wx_service.cart;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.CartExample;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.bean_for_wx_car.OrderBeanForCat;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CartService {

    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);


    BaseResponseModel<Map<String, Object>> indexListService(int userId);

    BaseResponseModel checkoutRespValue(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId,Integer userId);

    List<Cart> selectByUserId(@Param("userId") Integer userId);

    int updateByProductIdForDelete(@Param("productId") Integer productId);

    int updateByProductIdForChecked(@Param("productId") Integer productId,@Param("checked")Integer checked);

    List<Cart> selectByUserIdAndChecked(@Param("userId") Integer userId);

    Order addOrder(OrderBeanForCat orderBeanForCat);

    BaseResponseModel orderList(Integer showType, int page, int size);

    Cart addCart(Cart cart);


}

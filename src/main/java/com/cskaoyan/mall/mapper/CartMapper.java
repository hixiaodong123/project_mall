package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.CartExample;
import java.util.List;
import java.util.Map;

import com.cskaoyan.mall.bean.base.BaseResponseModel;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

public interface CartMapper {
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

    List<Cart> selectByUserId(@Param("userId") Integer userId);

    List<Cart> selectByUserIdAndChecked(@Param("userId") Integer userId,@Param("cartId")Integer cartId);

    int updateByProductIdForDelete(@Param("productId") Integer productId);

    int updateByProductIdForChecked(@Param("productId") Integer productId,@Param("checked")Integer checked);

    Cart selectByUserIdAndProductId(@Param("productId") Integer productId,@Param("userId")Integer userId);
}
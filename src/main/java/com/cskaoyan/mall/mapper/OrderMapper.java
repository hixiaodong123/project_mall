package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> queryOrderList(@Param("orderStatusArray") int orderStatusArray, @Param("sort") String sort,
                               @Param("order") String order, @Param("orderSn") String orderSn, @Param("userId") int userId);

    @Select("select count(*) from cskaoyan_mall_order")
    int queryAllOrderNum();
}
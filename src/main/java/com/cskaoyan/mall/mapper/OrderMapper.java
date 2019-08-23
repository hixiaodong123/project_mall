package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    int updateStatuAs301ByOrderId(@Param("id") int orderId, @Param("shipChannel") String shipChannel, @Param("shipSn") String shipSn);

    int updateStatuAs203ByOrderId(@Param("id") int orderId, @Param("refundMoney") int refundMoney);

    @Select("select count(*) from cskaoyan_mall_order where order_status = #{status}")
    long queryOrderStatusNum(@Param("status") int status);

    int updateOrderStatuByOrderId(@Param("order_status") int order_satus, @Param("ship_channel") String ship_channel, @Param("ship_sn") String ship_sn);

    @Select("select id from cskaoyan_mall_order where order_status = #{status} and deleted*1=0")
    List<Integer> queryOrderIdByStatus(@Param("status")int status);

    // List<Order> selectOrderIdByStatus(@Param("showType")Integer showType);

    int updataStatusByOrderId(@Param("status") int status,@Param("orderId")int orderId);

    int updateDeleteByOrderId(@Param("delete") boolean delete, @Param("orderId") int orderId);

    Order selectOrderByOrderSn(@Param("orderSn") String orderSn);
}
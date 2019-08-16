package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 首页显示条目的业务
 * @author: Lime
 * @create: 2019-08-16 13:44
 **/

@Service
public class IndexTotalService
{
    private final GoodsMapper goodsMapper;
    private final UserMapper userMapper;
    private final GoodsProductMapper goodsProductMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public IndexTotalService(GoodsMapper goodsMapper, UserMapper userMapper, GoodsProductMapper goodsProductMapper, OrderMapper orderMapper)
    {
        this.goodsMapper = goodsMapper;
        this.userMapper = userMapper;
        this.goodsProductMapper = goodsProductMapper;
        this.orderMapper = orderMapper;
    }


    public Long queryGoodsTotal()
    {
        return goodsMapper.countByExample(null);
    }

    public Long queryUserTotal()
    {
        return userMapper.countByExample(null);
    }

    public Long queryProductTotal()
    {
        return goodsProductMapper.countByExample(null);
    }

    public Long queryOrderTotal()
    {
        return orderMapper.countByExample(null);
    }
}

package com.cskaoyan.mall.test;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description: 测试类
 * @author: Lime
 * @create: 2019-08-16 12:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest
{
    @Autowired
    private GoodsMapper goodsMapper;


    @Test
    public void name()
    {
        //查询所有的订单,封装成List
        PageHelper.startPage(Integer.parseInt("1"), Integer.parseInt("10"));
        List<Goods> list = goodsMapper.selectByExample(null);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();

        for (Goods goods : list)
        {
            System.out.println(goods);
        }
        System.out.println(total);

    }
}

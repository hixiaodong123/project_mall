package com.cskaoyan.mall.test;

import com.cskaoyan.mall.mapper.GoodsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        long l = goodsMapper.countByExample(null);
        System.out.println(l);
    }
}

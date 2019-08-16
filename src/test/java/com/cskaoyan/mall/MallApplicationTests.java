package com.cskaoyan.mall;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests
{

    @Autowired
    AdMapper adMapper;

    @Test
    public void contextLoads()
    {
        Ad ad = adMapper.selectByPrimaryKey(1);
        System.out.println(ad);
    }

}

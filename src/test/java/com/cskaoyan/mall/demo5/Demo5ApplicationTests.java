package com.cskaoyan.mall.demo5;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.service.goods.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.cskaoyan.mall.mapper")
public class Demo5ApplicationTests {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Test
    public void contextLoads() {
        Map<String, Object> map = goodsService.selectAllGoodsList(1, 20,"add_time","desc");
        System.out.println(map.get("items"));
    }

    @Test
    public void test1(){
        /*DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.now();
        *//*System.out.println(date.format(pattern));*//*
        String format = date.format(pattern);*/

        Calendar instance = Calendar.getInstance();
        /*instance.add(Calendar.DATE,0);
        Date time = instance.getTime();*/
       /* int actualMaximum = instance.getActualMaximum(Calendar.D);*/
        /*System.out.println(actualMaximum);*/
        String str="2019-1-1";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse);
    }

}

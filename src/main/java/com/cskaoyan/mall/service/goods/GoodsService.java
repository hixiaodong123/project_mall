package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.UpdateGoods;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import java.util.List;

public interface GoodsService {

    Map<String,Object> selectGoodsByGoodSnAndName(int page,int limit,String name,String goodsSn,String sort,String order);

    Map<String,Object> selectAllGoodsList(int page, int limit,String sort,String order);

    Map<String,Object> selectGoodsByGoodSn(int page,int limit,String goodsSn,String sort,String order);

    Map<String,Object> selectGoodsByGoodsName(int page,int limit,String name,String sort,String order);

    Map<String,Object> selectAllCatAndBrand();

    Map<String,Object> returnGoodsDetail(int id);

    int deleteGoods(Goods goods);

    Map<String,Object> updateGoods(UpdateGoods map);

    Map<String,Object> createGoods(UpdateGoods map);
}

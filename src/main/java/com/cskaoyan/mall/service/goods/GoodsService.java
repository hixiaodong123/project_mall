package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;

import java.util.Map;

import java.util.List;

public interface GoodsService {

    Map<String,Object> selectGoodsByGoodSnAndName(int page,int limit,String name,String goodsSn,String sort,String order);

    Map<String,Object> selectAllGoodsList(int page, int limit,String sort,String order);

    Map<String,Object> selectGoodsByGoodSn(int page,int limit,String goodsSn,String sort,String order);

    Map<String,Object> selectAllCatAndBrand();

    Map<String,Object> returnGoodsDetail(int id);

    int deleteGoods(Goods goods);

}

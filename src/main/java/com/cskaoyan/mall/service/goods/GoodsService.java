package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.UpdateGoods;
import org.apache.ibatis.annotations.Param;
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

    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
}

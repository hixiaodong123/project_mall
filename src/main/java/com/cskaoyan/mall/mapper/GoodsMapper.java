package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface GoodsMapper {
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

    List<Goods> selectAllGoodsList(String sort,String order);

    @Select("select count(*) from cskaoyan_mall_goods")
    int selectAllGoodsNum();

    List<Goods> selectGoodsByGoodSn(String goodsSn,String sort,String order);

    List<Goods> selectGoodsByGoodSnAndName(String name,String goodsSn,String sort,String order);

    Goods selectGoodsByCategoryId(@Param("categoryId") int categoryId);

    List<Goods> selectGoodsByGoodsName(@Param("goodsName") String goodsName,String sort,String order);
}
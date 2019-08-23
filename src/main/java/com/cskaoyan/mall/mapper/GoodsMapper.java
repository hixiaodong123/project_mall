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

    List<Goods> selectAllGoodsList(@Param("sort") String sort,@Param("order") String order);


    int selectAllGoodsNum();

    List<Goods> selectGoodsByGoodSn(@Param("goodsSn") String goodsSn,
                                    @Param("sort") String sort,@Param("order") String order);

    List<Goods> selectGoodsByGoodSnAndName(@Param("name") String name, @Param("goodsSn") String goodsSn,
                                           @Param("sort") String sort,@Param("order") String order);

    Goods selectGoodsByCategoryId(@Param("categoryId") int categoryId);

    List<Goods> selectGoodsByGoodsName(@Param("goodsName") String goodsName,String sort,String order);

    List<Goods> queryGoodsByKeywordOrId(@Param("name") String keyword, @Param("sort") String sort,
                                        @Param("order") String order, @Param("id") int categoryId);

    List<Goods> queryGoodsListByCategoryId(@Param("category_id") int category_id);
}
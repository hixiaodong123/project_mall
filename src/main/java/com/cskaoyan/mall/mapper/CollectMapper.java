package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.CollectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CollectMapper
{
    long countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> listColletByCondition(@Param("userId") String userId, @Param("valueId") String valueId, @Param("sort") String sort, @Param("order") String order);


    @Update("update cskaoyan_mall_collect set deleted=true where value_id = ${valueId} and user_id = ${userId} and type = ${type}")
    void updateDelete(Integer valueId, Integer userId, Byte type);

    @Update("update cskaoyan_mall_collect set deleted=false where value_id = ${valueId} and user_id = ${userId} and type = ${type}")
    void updateAdd(Integer valueId, Integer userId, Byte type);

    @Select("select deleted from cskaoyan_mall_collect where value_id = ${valueId} and user_id = ${userId} and type = ${type}")
    short queryDeleted(Integer userId, Byte type, Integer valueId);
}
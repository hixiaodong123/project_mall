package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GrouponMapper {
    long countByExample(GrouponExample example);

    int deleteByExample(GrouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Groupon record);

    int insertSelective(Groupon record);

    List<Groupon> selectByExample(GrouponExample example);

    List<Groupon> selectByConditions(@Param("goodsId")Integer goodsId,
                                     @Param("sort")String sort,
                                     @Param("order")String order);

    Groupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Groupon record, @Param("example") GrouponExample example);

    int updateByExample(@Param("record") Groupon record, @Param("example") GrouponExample example);

    int updateByPrimaryKeySelective(Groupon record);

    int updateByPrimaryKey(Groupon record);
}
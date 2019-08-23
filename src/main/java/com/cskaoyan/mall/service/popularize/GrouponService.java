package com.cskaoyan.mall.service.popularize;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponExample;
import com.cskaoyan.mall.bean.wx.GrouponWX;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GrouponService {
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

    Map<String,Object> returnGrouponList(int showType);

    Map<String,Object> returnGrouponDetail(int grouponId);
}

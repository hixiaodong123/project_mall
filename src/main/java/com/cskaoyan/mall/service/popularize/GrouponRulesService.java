package com.cskaoyan.mall.service.popularize;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.GrouponRulesExample;
import com.cskaoyan.mall.bean.wx.GrouponWX;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRulesService {

    List<GrouponRules> selectByConditions(@Param("goodsId") Integer goodsId, @Param("sort") String sort, @Param("order") String order);

    long countByExample(GrouponRulesExample example);

    int deleteByExample(GrouponRulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    List<GrouponRules> selectByExample(GrouponRulesExample example);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByExample(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponWX> selectGrouponWXList();
}

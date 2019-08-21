package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Api;
import com.cskaoyan.mall.bean.ApiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiMapper {
    long countByExample(ApiExample example);

    int deleteByExample(ApiExample example);

    int deleteByPrimaryKey(Integer apiid);

    int insert(Api record);

    int insertSelective(Api record);

    List<Api> selectByExample(ApiExample example);

    Api selectByPrimaryKey(Integer apiid);

    int updateByExampleSelective(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByExample(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKey(Api record);
}
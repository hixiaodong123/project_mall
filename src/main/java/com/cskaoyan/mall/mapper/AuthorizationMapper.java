package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Authorization;
import com.cskaoyan.mall.bean.AuthorizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorizationMapper {
    long countByExample(AuthorizationExample example);

    int deleteByExample(AuthorizationExample example);

    int deleteByPrimaryKey(Integer autid);

    int insert(Authorization record);

    int insertSelective(Authorization record);

    List<Authorization> selectByExample(AuthorizationExample example);

    Authorization selectByPrimaryKey(Integer autid);

    int updateByExampleSelective(@Param("record") Authorization record, @Param("example") AuthorizationExample example);

    int updateByExample(@Param("record") Authorization record, @Param("example") AuthorizationExample example);

    int updateByPrimaryKeySelective(Authorization record);

    int updateByPrimaryKey(Authorization record);
}
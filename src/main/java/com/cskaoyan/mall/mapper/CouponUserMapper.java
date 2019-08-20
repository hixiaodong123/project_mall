package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.CouponUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponUserMapper {
    long countByExample(CouponUserExample example);

    int deleteByExample(CouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    List<CouponUser> selectByExample(CouponUserExample example);

    List<CouponUser> selectByConditions(@Param("couponId") Integer couponId,
                                      @Param("userId") Integer userId,
                                      @Param("status") Integer status,
                                      @Param("sort") String sort,
                                      @Param("order") String order);

    CouponUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByExample(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);
}
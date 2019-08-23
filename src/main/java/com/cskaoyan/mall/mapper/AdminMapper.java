package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    String queryPasswordByName(@Param("username") String username);

    int[] selectRolesIdByName(@Param("username") String username);

    Admin queryUserByName(@Param("username")String username);


}
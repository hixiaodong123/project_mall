package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.AddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressService {
    List<Address> listAddressByCondition(String userId,String name,String sort,String order);

    List<Address> selectAddressByUserId(@Param("userId") Integer userId);

    int updateByAddressIdForDelete(@Param("addressId") Integer addressId);

    int insert(Address record);

    long countByExample(AddressExample example);
}

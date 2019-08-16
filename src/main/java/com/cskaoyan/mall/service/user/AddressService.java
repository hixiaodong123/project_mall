package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.Address;

import java.util.List;

public interface AddressService {
    List<Address> listAddressByCondition(String userId,String name);
}

package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.AddressExample;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> listAddressByCondition(String userId, String name,String sort, String order) {
        List<Address> users = addressMapper.listAddressByCondition(userId,name,sort,order);
        return users;
    }
}
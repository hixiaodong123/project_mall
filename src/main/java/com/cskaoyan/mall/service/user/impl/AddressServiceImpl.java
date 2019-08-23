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

    @Override
    public List<Address> selectAddressList() {
        return addressMapper.selectAllAddress();
    }

    @Override
    public Address selectAddressById(int id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAddress(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public int deleteAdminById(int id) {
        return addressMapper.deleteAddressById(id);
    }

    @Override
    public List<Address> selectAddressByUserId(Integer userId) {
        return addressMapper.selectAddressByUserId(userId);
    }

    @Override
    public int updateByAddressIdForDelete(Integer addressId) {
        return addressMapper.updateByAddressIdForDelete(addressId);
    }

    @Override
    public int insert(Address record) {
        return addressMapper.insert(record);
    }

    @Override
    public long countByExample(AddressExample example) {
        return addressMapper.countByExample(example);
    }
}

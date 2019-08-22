package com.cskaoyan.mall.controller.webcontroller.user;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.user.AddressService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @RequestMapping("/list")
    public BaseResponseModel<Map<String,Object>> listAddress(int page, int limit, String userId, String name, String sort, String order){
        //首先判断userId和name字段是否填写,若未填写则将其设为空字符串，否则直接进行模糊查询
        userId = BaseEncapsulation.judgeString(userId);
        name = BaseEncapsulation.judgeString(name);
        //按页查询address
        PageHelper.startPage(page,limit);
        List<Address> addressList = addressService.listAddressByCondition("%" + userId + "%","%" + name + "%",sort,order);
        BaseResponseModel<Map<String, Object>> encapsulation = BaseEncapsulation.encapsulation(addressList);
        return encapsulation;
    }
}

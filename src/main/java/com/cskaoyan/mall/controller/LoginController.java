package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.base.TotalInfo;
import com.cskaoyan.mall.bean.base.UserInfo;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.service.IndexTotalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Lime
 * @create: 2019-08-16 11:15
 **/

@RestController
@RequestMapping("/admin")
public class LoginController
{

    private final IndexTotalService indexTotalService;

    @Autowired
    public LoginController(IndexTotalService indexTotalService)
    {
        this.indexTotalService = indexTotalService;
    }


    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public BaseResponseModel<String> login(UserInfo userInfo)
    {

        return new BaseResponseModel<>("5aa0ae65-914f-4d3c-9fbf-82f87628218b", "成功", 0);
    }

    @RequestMapping(value = "/auth/info", method = RequestMethod.GET)
    public BaseResponseModel info()
    {
        BaseResponseModel<UserInfo> baseResponseModel = new BaseResponseModel<>();

        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName("hello");
        userInfo.setPerms(new String[]{"*"});
        userInfo.setRoles(new String[]{"超级管理员"});

        baseResponseModel.setData(userInfo);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);

        return baseResponseModel;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public BaseResponseModel dashboard()
    {

        Long totalGoods = indexTotalService.queryGoodsTotal();
        Long totalUser = indexTotalService.queryUserTotal();
        Long totalProduct = indexTotalService.queryProductTotal();
        Long totalOrder = indexTotalService.queryOrderTotal();

        TotalInfo totalInfo = new TotalInfo(totalGoods, totalUser, totalProduct, totalOrder);
        BaseResponseModel<TotalInfo> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(totalInfo);

        return baseResponseModel;

    }




}

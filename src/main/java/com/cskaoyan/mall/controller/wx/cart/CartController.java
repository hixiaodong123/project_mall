package com.cskaoyan.mall.controller.wx.cart;

import com.cskaoyan.mall.bean.base.BaseResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/cart")
public class CartController {

    @RequestMapping("/goodscount")
    public BaseResponseModel goodsCount(){
        BaseResponseModel<Integer> responseModel = new BaseResponseModel<>();
        responseModel.setErrno(0);
        responseModel.setErrmsg("成功");
        responseModel.setData(4);
        return responseModel;
    }
}

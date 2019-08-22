package com.cskaoyan.mall.controller.wx.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/goods")
public class WXGoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/count")
    public BaseResponseModel countGoods(){
        PageHelper.startPage(0,100);
        List<Goods> goods = goodsService.selectAllGoods();
        BaseResponseModel<Map<String, Object>> responseModel = new BaseResponseModel<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsCount",goods.size());
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        responseModel.setData(map);
        return responseModel;
    }
}

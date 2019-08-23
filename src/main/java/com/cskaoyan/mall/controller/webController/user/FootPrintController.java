package com.cskaoyan.mall.controller.webcontroller.user;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.user.FootPrintService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/footprint")
public class FootPrintController {
    @Autowired
    FootPrintService footPrintService;

    @RequestMapping("/list")
    public BaseResponseModel<Map<String,Object>> listFootPrint(int page, int limit, String userId, String goodsId, String sort, String order){
        //首先判断userId和goodsId字段是否填写,若未填写则将其设为空字符串，否则直接进行模糊查询
        userId = BaseEncapsulation.judgeString(userId);
        goodsId = BaseEncapsulation.judgeString(goodsId);
        //按页查询footPrint
        PageHelper.startPage(page,limit);
        List<Footprint> footprintList = footPrintService.listFootPrintByCondition("%" + userId + "%","%" + goodsId + "%", sort, order);
        BaseResponseModel<Map<String, Object>> encapsulation = BaseEncapsulation.encapsulation(footprintList);
        return encapsulation;
    }
}

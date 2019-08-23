package com.cskaoyan.mall.controller.webcontroller.user;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.user.CollectService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/collect")
public class CollectController {
    @Autowired
    CollectService collectService;

    @RequestMapping("/list")
    public BaseResponseModel<Map<String,Object>> listCollect(int page, int limit, String userId, String valueId, String sort, String order){
        //首先判断userId和valueId字段是否填写,若未填写则将其设为空字符串，否则直接进行模糊查询
        userId = BaseEncapsulation.judgeString(userId);
        valueId = BaseEncapsulation.judgeString(valueId);
        //按页查询collect
        PageHelper.startPage(page,limit);
        List<Collect> collectList = collectService.listColletByCondition("%" + userId + "%","%" + valueId + "%", sort, order);
        BaseResponseModel<Map<String, Object>> encapsulation = BaseEncapsulation.encapsulation(collectList);
        return encapsulation;
    }
}

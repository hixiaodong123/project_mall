package com.cskaoyan.mall.controller.webcontroller.user;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.user.SearchHistoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/history")
public class SearchHistoryController {
    @Autowired
    SearchHistoryService searchHistoryService;

    @RequestMapping("/list")
    public BaseResponseModel<Map<String,Object>> listSearchHistory(int page, int limit, String userId, String keyword, String sort, String order){
        //首先判断username和mobile字段是否填写,若未填写则将其设为空字符串，否则直接进行模糊查询
        userId = BaseEncapsulation.judgeString(userId);
        keyword = BaseEncapsulation.judgeString(keyword);
        //按页查询user
        PageHelper.startPage(page,limit);
        List<SearchHistory> searchHistoryList = searchHistoryService.listSearchHistoryByCondition("%" + userId + "%","%" + keyword + "%", sort, order);
        //封装BaseResponseModel
        BaseResponseModel<Map<String, Object>> encapsulation = BaseEncapsulation.encapsulation(searchHistoryList);
        return encapsulation;
    }
}

package com.cskaoyan.mall.controller.wx.wxsearch;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.KeywordService;
import com.cskaoyan.mall.service.user.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXSearchController {
    @Autowired
    KeywordService keywordService;

    @Autowired
    SearchHistoryService searchHistoryService;

    @RequestMapping("search/index")
    public BaseResponseModel searchIndex() {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Map<String, Object> data = new HashMap<>();
        Keyword defaultKeyword = keywordService.selectByPrimaryKey(6);
        List<Keyword> hotKeywordList = keywordService.queryHotKeyword();
        List historyKeywordList = new ArrayList();
        data.put("defaultKeyword", defaultKeyword);
        data.put("historyKeywordList", historyKeywordList);
        data.put("hotKeywordList", historyKeywordList);
        baseResponseModel.setData(data);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }

    @RequestMapping("search/helper")
    public BaseResponseModel searcherHelper(String keyword) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        List<String> queryKeyword = keywordService.queryKeyword(keyword);
        Map<Object, Object> data = new HashMap<>();
        for(String s : queryKeyword) {
            data.put(queryKeyword.indexOf(s), s);
        }
        baseResponseModel.setData(data);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }
}

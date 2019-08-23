package com.cskaoyan.mall.controller.wx.wxsearch;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.KeywordService;
import com.cskaoyan.mall.service.user.SearchHistoryService;
import com.cskaoyan.mall.utils.wx_util.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/search")
public class WXSearchController {
    @Autowired
    KeywordService keywordService;

    @Autowired
    SearchHistoryService searchHistoryService;

    @RequestMapping("/index")
    public BaseResponseModel indexSearch(HttpServletRequest request){
        List<Keyword> rootKeyList = keywordService.queryKeywordList(null,null,null,null);

        Keyword defaultKeywordList = new Keyword();
        ArrayList<Keyword> hotKeywordList = new ArrayList<>();
        for (Keyword keyword : rootKeyList) {
            //1.defaultKeyword 根据cskaoyan_mall_keyword表查询is_default为true的数据
            if(keyword.getIsDefault()){
                defaultKeywordList = keyword;
            }
            //3.hotKeywordList 根据cskaoyan_mall_keyword表查询is_hot为true的数据
            if(keyword.getIsHot()){
                hotKeywordList.add(keyword);
            }
        }
        //2.historyKeywordList 根据cskaoyan_mall_search_history表查询，同时点击查询后，需要增加相应数据
        //获得userId
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        List<SearchHistory> historyKeywordList = new ArrayList<>();
        if(userId != null){
            historyKeywordList = searchHistoryService.listSearchHistoryByCondition(userId + "", "%%", "add_time", "desc");
        }else{
            historyKeywordList = searchHistoryService.listSearchHistoryByCondition("999", "%%", "add_time", "desc");
        }
        BaseResponseModel<Map> responseModel = new BaseResponseModel<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("defaultKeyword",defaultKeywordList);
        map.put("historyKeywordList",historyKeywordList);
        map.put("hotKeywordList",hotKeywordList);
        responseModel.setData(map);
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        return responseModel;
    }

    @RequestMapping("/clearhistory")
    public Map clearHistoryByUserId(HttpServletRequest request){
        //获得userId
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        int result;
        if(userId != null){
            result = searchHistoryService.clearHistoryByUserId(userId);
        }else{
            result = searchHistoryService.clearHistoryByUserId(999);
        }
        HashMap<String, Object> map = new HashMap<>();
        if(result == 1){
            map.put("errmsg","成功");
            map.put("errno",0);
        }else{
            map.put("errmsg","失败");
            map.put("errno",401);
        }
        return map;
    }

    @RequestMapping("/helper")
    public BaseResponseModel searcherHelper(String keyword) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        List<String> queryKeyword = keywordService.queryKeyword(keyword);
        baseResponseModel.setData(queryKeyword);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }
}

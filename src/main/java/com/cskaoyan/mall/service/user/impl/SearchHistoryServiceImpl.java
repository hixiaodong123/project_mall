package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.bean.SearchHistoryExample;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.user.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {
    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<SearchHistory> listSearchHistoryByCondition(String userId, String keyword, String sort, String order) {
        List<SearchHistory> searchHistoryList = searchHistoryMapper.listSearchHistoryByCondition(userId,keyword,sort,order);
        return searchHistoryList;
    }

    @Override
    public int clearHistoryByUserId(int userId) {
        SearchHistoryExample historyExample = new SearchHistoryExample();
        historyExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<SearchHistory> searchHistoryList = searchHistoryMapper.selectByExample(historyExample);
        int update = 0;
        for (SearchHistory searchHistory : searchHistoryList) {
            searchHistory.setDeleted(true);
            int result = searchHistoryMapper.updateByPrimaryKey(searchHistory);
            update += result;
        }
        return update == searchHistoryList.size() ? 1 : 0;
    }

    @Override
    public void insertSearchHistory(String keyword, int userId) {
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        searchHistoryExample.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId).andKeywordEqualTo(keyword);
//        if(keyword != null){
//            searchHistoryExample.createCriteria().andKeywordEqualTo(keyword);
//        }
        List<SearchHistory> list = searchHistoryMapper.selectByExample(searchHistoryExample);
        if(list == null || list.size() == 0){
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setKeyword(keyword);
            searchHistory.setDeleted(false);
            searchHistory.setUserId(userId);
            Date date = new Date(System.currentTimeMillis());
            searchHistory.setAddTime(date);
            searchHistory.setUpdateTime(date);
            searchHistory.setFrom("wx");
            searchHistoryMapper.insert(searchHistory);
        }
    }
}

package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.user.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

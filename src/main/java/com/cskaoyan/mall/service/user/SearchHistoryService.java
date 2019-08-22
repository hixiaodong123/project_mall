package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.SearchHistory;

import java.util.List;

public interface SearchHistoryService {
    List<SearchHistory> listSearchHistoryByCondition(String userId, String keyword, String sort, String order);

    int clearHistoryByUserId(int userId);

    void insertSearchHistory(String keyword, int usrId);
}

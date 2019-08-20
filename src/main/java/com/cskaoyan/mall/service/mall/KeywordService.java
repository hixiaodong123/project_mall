package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Keyword;

import java.util.List;

public interface KeywordService {
    List<Keyword> queryKeywordList(String keyword, String url, String sort, String order);

    int updateByPrimaryKeySelective(Keyword keyword);

    Keyword selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Keyword keyword);

    Keyword queryKeywordByKeywordAndUrl(String keyword, String url);
}

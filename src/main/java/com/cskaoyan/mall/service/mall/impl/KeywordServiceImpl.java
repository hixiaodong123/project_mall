package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.service.mall.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public List<Keyword> queryKeywordList(String keyword, String url, String sort, String order) {
        List<Keyword> keywords = keywordMapper.queryKeywordList(keyword, url, sort, order);
        return keywords;
    }

    @Override
    public int updateByPrimaryKeySelective(Keyword keyword) {
        int i = keywordMapper.updateByPrimaryKeySelective(keyword);
        return i;
    }

    @Override
    public Keyword selectByPrimaryKey(Integer id) {
        Keyword keyword = keywordMapper.selectByPrimaryKey(id);
        return keyword;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = keywordMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insert(Keyword keyword) {
        int insert = keywordMapper.insert(keyword);
        return insert;
    }

    @Override
    public Keyword queryKeywordByKeywordAndUrl(String keyword, String url) {
        Keyword queryKeyword = keywordMapper.queryKeywordByKeywordAndUrl(keyword, url);
        return queryKeyword;
    }
}

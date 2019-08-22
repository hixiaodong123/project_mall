package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.KeywordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KeywordMapper {
    long countByExample(KeywordExample example);

    int deleteByExample(KeywordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    List<Keyword> selectByExample(KeywordExample example);

    Keyword selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByExample(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

    List<Keyword> queryKeywordList(@Param("keyword") String keyword, @Param("url") String url, @Param("sort") String sort, @Param("order") String order);

    Keyword queryKeywordByKeywordAndUrl(@Param("keyword") String keyword, @Param("url") String url);

    List<Keyword> queryHotKeyword();

    List<String> queryKeyword(@Param("keyword") String keyword);
}
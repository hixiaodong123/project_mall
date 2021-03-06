package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.IssueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IssueMapper {
    long countByExample(IssueExample example);

    int deleteByExample(IssueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Issue record);

    int insertSelective(Issue record);

    List<Issue> selectByExample(IssueExample example);

    Issue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Issue record, @Param("example") IssueExample example);

    int updateByExample(@Param("record") Issue record, @Param("example") IssueExample example);

    int updateByPrimaryKeySelective(Issue record);

    int updateByPrimaryKey(Issue record);

    List<Issue> queryIssueList(@Param("question") String question, @Param("sort") String sort, @Param("order") String order);

    Issue queryIssueByAnswerAndQuestion(@Param("answer") String answer, @Param("question") String question);
}
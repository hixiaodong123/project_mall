package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> queryIssueList(String question, String sort, String order);

    int updateByPrimaryKeySelective(Issue issue);

    Issue selectByPrimaryKey(int id);

    int deleteByPrimaryKey(Integer id);

    int insert(Issue issue);

    Issue queryIssueByAnswerAndQuestion(String answer, String question);
}

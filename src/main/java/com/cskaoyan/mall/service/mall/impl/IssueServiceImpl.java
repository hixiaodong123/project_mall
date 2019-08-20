package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.mapper.IssueMapper;
import com.cskaoyan.mall.service.mall.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;

    @Override
    public List<Issue> queryIssueList(String question, String sort, String order) {
        List<Issue> issues = issueMapper.queryIssueList(question, sort, order);
        return issues;
    }

    @Override
    public int updateByPrimaryKeySelective(Issue issue) {
        int update = issueMapper.updateByPrimaryKeySelective(issue);
        return update;
    }

    @Override
    public Issue selectByPrimaryKey(int id) {
        Issue issue = issueMapper.selectByPrimaryKey(id);
        return issue;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = issueMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insert(Issue issue) {
        int insert = issueMapper.insert(issue);
        return insert;
    }

    @Override
    public Issue queryIssueByAnswerAndQuestion(String answer, String question) {
        Issue issue = issueMapper.queryIssueByAnswerAndQuestion(answer, question);
        return issue;
    }
}

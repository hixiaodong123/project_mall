package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class IssueController {
    @Autowired
    IssueService issueService;

    @RequestMapping("issue/list")
    public BaseResponseModel list(int page, int limit, String question, String sort, String order) {
        List<Issue> issues = issueService.queryIssueList(question, sort, order);
        Map data = new HashMap<>();
        data.put("items", issues);
        data.put("total", issues.size());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setData(data);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping("issue/update")
    public BaseResponseModel update(@RequestBody Issue issue) {
        int i = issueService.updateByPrimaryKeySelective(issue);
        Issue queryIssue = issueService.selectByPrimaryKey(issue.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(i == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setData(queryIssue);
        }
        return baseResponseModel;
    }

    @RequestMapping("issue/delete")
    public BaseResponseModel delete(@RequestBody Issue issue) {
        int i = issueService.deleteByPrimaryKey(issue.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(i == 1) {
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
        }
        return baseResponseModel;
    }

    @RequestMapping("issue/create")
    public BaseResponseModel create(@RequestBody Issue issue) {
        int insert = issueService.insert(issue);
        Issue queryIssue = issueService.queryIssueByAnswerAndQuestion(issue.getAnswer(), issue.getQuestion());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(insert == 1) {
            baseResponseModel.setData(queryIssue);
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
        }
        return baseResponseModel;
    }
}

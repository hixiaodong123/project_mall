package com.cskaoyan.mall.controller.webcontroller.mall;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.page.PageBean;
import com.cskaoyan.mall.service.mall.KeywordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class KeywordController {
    @Autowired
    KeywordService keywordService;

    @RequestMapping("keyword/list")
    public BaseResponseModel list(int page, int limit, String keyword, String url, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Keyword> keywords = keywordService.queryKeywordList(keyword, url, sort, order);
        PageInfo<Keyword> pageInfo = new PageInfo<>(keywords);
        PageBean<Keyword> data = new PageBean<>(keywords, pageInfo.getTotal());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(data);
        return baseResponseModel;
    }

    @RequestMapping("keyword/update")
    public BaseResponseModel update(@RequestBody Keyword keyword) {
        int i = keywordService.updateByPrimaryKeySelective(keyword);
        Keyword queryKeyword = keywordService.selectByPrimaryKey(keyword.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(i == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setData(queryKeyword);
        }
        return baseResponseModel;
    }

    @RequestMapping("keyword/delete")
    public BaseResponseModel delete(@RequestBody Keyword keyword) {
        int i = keywordService.deleteByPrimaryKey(keyword.getId());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(i == 1) {
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
        }
        return baseResponseModel;
    }

    @RequestMapping("keyword/create")
    public BaseResponseModel create(@RequestBody Keyword keyword) {
        keyword.setSortOrder(100);
        int insert = keywordService.insert(keyword);
        Keyword queryKeyword = keywordService.queryKeywordByKeywordAndUrl(keyword.getKeyword(), keyword.getUrl());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if(insert == 1) {
            baseResponseModel.setData(queryKeyword);
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
        }
        return baseResponseModel;
    }
}

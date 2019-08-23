package com.cskaoyan.mall.service.person.feedback.impl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.person.feedback.WxFeedbackService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户反馈业务实现类
 * @author: Lime
 * @create: 2019-08-23 16:59
 **/

@Service
public class WxFeedbackServiceImpl implements WxFeedbackService
{
    private final FeedbackMapper feedbackMapper;

    @Autowired
    public WxFeedbackServiceImpl(FeedbackMapper feedbackMapper)
    {
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public void insert(Feedback feedback)
    {
        feedbackMapper.insert(feedback);

    }
}

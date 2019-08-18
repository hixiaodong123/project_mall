package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.user.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> listFeedbackByCondition(String username, String id, String sort, String order) {
        List<Feedback> feedbackList = feedbackMapper.listFeedbackByCondition(username, id, sort, order);
        return feedbackList;
    }
}

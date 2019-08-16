package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> listFeedbackByCondition(String username, String id);
}

package com.cskaoyan.mall.controller.webController.user;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.user.FeedbackService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @RequestMapping("/list")
    public BaseResponseModel<Map<String,Object>> listFeedback(int page, int limit, String username, String id, String sort, String order){
        //首先判断userId和valueId字段是否填写,若未填写则将其设为空字符串，否则直接进行模糊查询
        username = BaseEncapsulation.judgeString(username);
        id = BaseEncapsulation.judgeString(id);
        //按页查询collect
        PageHelper.startPage(page,limit);
        List<Feedback> feedbackList = feedbackService.listFeedbackByCondition("%" + username + "%","%" + id + "%", sort, order);
        BaseResponseModel<Map<String, Object>> encapsulation = BaseEncapsulation.encapsulation(feedbackList);
        return encapsulation;
    }
}

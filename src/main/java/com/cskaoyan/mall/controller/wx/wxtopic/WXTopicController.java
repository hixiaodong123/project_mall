package com.cskaoyan.mall.controller.wx.wxtopic;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("wx")
@RestController
public class WXTopicController {
    @Autowired
    TopicService topicService;

    @RequestMapping("topic/detail")
    public BaseResponseModel topicDetail(int id) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Map<String, Object> data = new HashMap<>();
        Topic topic = topicService.selectByPrimaryKey(id);
        List goods = new ArrayList();
        data.put("topic", topic);
        data.put("goods", goods);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(data);
        return baseResponseModel;
    }

    @RequestMapping("topic/related")
    public BaseResponseModel topicRelated(int id) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        List<Topic> data = topicService.queryTopicRelated(id);
        baseResponseModel.setData(data);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }
}

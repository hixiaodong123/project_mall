package com.cskaoyan.mall.controller.webController.popularize;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.TopicService;
import com.cskaoyan.mall.utils.popularize.PopularizeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public BaseResponseModel<Map<String,Object>> list(int page, int limit,String title, String subtitle, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Topic> topics = topicService.selectByConditions(title, subtitle, sort, order);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("items", topics);
        map.put("total", total);
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setData(map);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public BaseResponseModel<Topic> creat(@RequestBody Topic topic) {
        topic.setAddTime(new Date());
        topic.setUpdateTime(new Date());
        topic.setDeleted(false);
        topic.setSortOrder(0);
        int insert = topicService.insert(topic);
        Topic topic1 = topicService.selectByTitle(topic.getTitle());
        BaseResponseModel<Topic> topicBaseResponseModel = PopularizeUtils.respValue(topic1, insert);
        return topicBaseResponseModel;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResponseModel<Topic> update(@RequestBody Topic topic) {
        topic.setUpdateTime(new Date());
        int update = topicService.updateByPrimaryKeySelective(topic);
        BaseResponseModel<Topic> couponBaseResponseModel = PopularizeUtils.respValue(topic, update);
        return couponBaseResponseModel;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestBody Topic topic) {
        topic.setDeleted(true);
        int update = topicService.updateByPrimaryKeySelective(topic);
        BaseResponseModel<Topic> topicBaseResponseModel = PopularizeUtils.respValue(topic, update);
        HashMap<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }
}

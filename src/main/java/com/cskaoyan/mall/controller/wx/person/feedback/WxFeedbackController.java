package com.cskaoyan.mall.controller.wx.person.feedback;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.person.feedback.WxFeedbackService;
import com.cskaoyan.mall.utils.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 微信端用户反馈的控制器
 * @author: Lime
 * @create: 2019-08-23 16:51
 **/


@RestController
@RequestMapping("/wx")
public class WxFeedbackController
{
    private final UserMapper userMapper;

    private final WxFeedbackService wxFeedbackService;

    @Autowired
    public WxFeedbackController(WxFeedbackService wxFeedbackService, UserMapper userMapper)
    {
        this.wxFeedbackService = wxFeedbackService;
        this.userMapper = userMapper;
    }


    @PostMapping("/feedback/submit")
    public Map<String, Object> feedback(@RequestBody Feedback feedback, HttpServletRequest request)
    {

        //获取userId
        String token = request.getHeader("X-Litemall-Token");
        Integer userId = null;
        if (token != null && !"".equals(token))
        {
            userId = UserTokenManager.getUserId(token);
        }

        User user = userMapper.selectByPrimaryKey(userId);
        feedback.setUserId(user.getId());
        feedback.setUsername(user.getUsername());
        feedback.setAddTime(new Date());
        feedback.setUpdateTime(new Date());
        feedback.setDeleted(false);
        feedback.setStatus(0);
        wxFeedbackService.insert(feedback);
        HashMap<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }
}


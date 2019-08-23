package com.cskaoyan.mall.controller.wx.wxcomment;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.login.UserInfo;
import com.cskaoyan.mall.bean.page.PageBean;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.service.user.UserService;
import com.cskaoyan.mall.utils.wx_util.UserTokenManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("wx")
public class WXCommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping("comment/list")
    public BaseResponseModel commentList(int valueId, int type, int size, int page, int showType) {
        PageHelper.startPage(page, size);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Set<Integer> set = commentService.queryUserIdByValueId(valueId, type);
        UserInfo userInfo = new UserInfo();
        List data = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int userId : set) {
            User user = userService.selectByPrimaryKey(userId);
            userInfo.setAvatarUrl(user.getAvatar());
            userInfo.setNickName(user.getNickname());
            // 确保得到同一个人的评论
            List<Comment> comments = commentService.queryComment(valueId, type, showType, userId);
            // 把每个评论连同userInfo重新封装成一个map
            for(Comment comment : comments) {
                Map<String, Object> map = new HashMap<>();
                map.put("addTime", simpleDateFormat.format(comment.getAddTime()));
                map.put("content", comment.getContent());
                map.put("picList", comment.getPicUrls());
                map.put("userInfo", userInfo);
                data.add(map);
            }
        }
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> map = new HashMap<>();
        map.put("count", pageInfo.getTotal());
        map.put("currentPage", page);
        map.put("data", data);
        baseResponseModel.setData(map);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }

    @RequestMapping("comment/post")
    public BaseResponseModel commentPost(@RequestBody Comment comment, HttpServletRequest request) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        String token = request.getHeader("X-Litemall-Token");
        Integer userId = null;
        if (token != null && !"".equals(token))
        {
            userId = UserTokenManager.getUserId(token);
            comment.setUserId(userId);
        }
        comment.setAddTime(new Date(System.currentTimeMillis()));
        int insert = commentService.insertComment(comment);
        Comment selectComment = commentService.selectComment(comment);
        if(insert == 1) {
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
            baseResponseModel.setData(selectComment);
        }
        return baseResponseModel;
    }
}

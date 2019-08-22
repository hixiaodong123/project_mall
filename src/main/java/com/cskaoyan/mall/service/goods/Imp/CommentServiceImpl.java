package com.cskaoyan.mall.service.goods.Imp;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.goods.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Map<String, Object> selectGoodsCommentByUserIdAndValueId(int page, int limit, String userId, String valueId, String sort, String order) {
        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectGoodsCommentByUserIdAndValueId(userId,valueId,sort,order);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",comments);
        return map;
    }

    @Override
    public Map<String, Object> selectGoodsCommentByUserId(int page, int limit, String userId, String sort, String order) {
        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectGoodsCommentByUserId(userId,sort,order);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",comments);
        return map;
    }

    @Override
    public Map<String, Object> selectGoodsCommentByValueId(int page, int limit, String valueId, String sort, String order) {
        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectGoodsCommentByValueId(valueId,sort,order);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",comments);
        return map;
    }

    @Override
    public Map<String, Object> selectGoodsCommentByValueId(String valueId) {
        List<Comment> comments = commentMapper.selectGoodsCommentByValueId(valueId,"add_time","desc");
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",comments);
        return map;
    }

    @Override
    public Map<String, Object> selectAllGoodsCommentList(int page, int limit, String sort, String order) {
        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectGoodsComment(sort,order);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        int total=commentMapper.selectAllGoodsCommentNum();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",comments);
        return map;
    }

    @Override
    public int deleteComment(Comment comment) {
        comment.setDeleted(true);
        int i = commentMapper.updateByPrimaryKey(comment);
        return i;
    }
}

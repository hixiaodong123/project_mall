package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CommentService {

    Map<String,Object> selectGoodsCommentByUserIdAndValueId(int page, int limit, String userId, String valueId, String sort, String order);

    Map<String,Object> selectGoodsCommentByUserId(int page, int limit, String userId,  String sort, String order);

    Map<String,Object> selectGoodsCommentByValueId(int page, int limit, String valueId,  String sort, String order);

    Map<String,Object> selectGoodsCommentByValueId(String valueId);

    Map<String,Object> selectAllGoodsCommentList(int page, int limit, String sort, String order);

    int deleteComment(Comment comment);

    int insertComment(Comment comment);

    Set<Integer> queryUserIdByValueId(int valueId, int type);

    List<Comment> queryComment(int valueId, int type, int showType, int userId);

    Comment selectComment(Comment comment);
}

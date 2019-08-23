package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Comment;

import java.util.Map;

public interface CommentService {

    Map<String,Object> selectGoodsCommentByUserIdAndValueId(int page, int limit, String userId, String valueId, String sort, String order);

    Map<String,Object> selectGoodsCommentByUserId(int page, int limit, String userId,  String sort, String order);

    Map<String,Object> selectGoodsCommentByValueId(int page, int limit, String valueId,  String sort, String order);

    Map<String,Object> selectGoodsCommentByValueId(String valueId);

    Map<String,Object> selectAllGoodsCommentList(int page, int limit, String sort, String order);

    int deleteComment(Comment comment);

    int insertComment(Comment comment);

}

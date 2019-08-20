package com.cskaoyan.mall.controller.goods;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("list")
    public Map<String,Object> returnGoodsCommentByUserIdOrByValueId(int page, int limit, String userId, String valueId, String sort, String order){
        if(userId!=null && valueId != null){
            Map<String, Object> map1 = commentService.selectGoodsCommentByUserIdAndValueId(page, limit, userId, valueId, sort, order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        } else if(userId!=null){
            Map<String, Object> map1 = commentService.selectGoodsCommentByUserId(page, limit,userId,sort,order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        }else if(valueId != null){
            Map<String, Object> map1 = commentService.selectGoodsCommentByValueId(page, limit,valueId,sort,order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        }else{
            Map<String, Object> map1 = commentService.selectAllGoodsCommentList(page, limit,sort,order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        }
    }

    @RequestMapping("delete")
    public Map<String,Object> deleteComment(@RequestBody Comment comment){
        int i=commentService.deleteComment(comment);
        if(i==1){
            return ReturnMapUntil.returnMap(null,"成功",0);
        }else{
            return ReturnMapUntil.returnMap(null,"失败",200);
        }
    }
}

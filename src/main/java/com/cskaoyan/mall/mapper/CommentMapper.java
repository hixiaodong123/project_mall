package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectGoodsCommentByUserIdAndValueId(String userId,String valueId,String sort,String order);

    List<Comment> selectGoodsCommentByUserId(String userId ,String sort,String order);

    List<Comment> selectGoodsCommentByValueId(String valueId ,String sort,String order);

    List<Comment> selectGoodsComment(@Param("sort") String sort,@Param("order") String order);

    @Select("select count(*) from cskaoyan_mall_comment")
    int selectAllGoodsCommentNum();
}
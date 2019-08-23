package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import java.util.List;
import java.util.Set;

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

    Set<Integer> queryUserIdByValueId(@Param("valueId") int valueId, @Param("type") int type);

    List<Comment> queryComment(@Param("valueId") int valueId, @Param("type") int type, @Param("showType") int showType,
                               @Param("userId") int userId);

    Comment selectComment(@Param("content") String content, @Param("hasPicture") Boolean hasPicture,
                         @Param("picUrls") String[] picUrls, @Param("star") Short star, @Param("type") int type, @Param("valueId") int valueId);


}
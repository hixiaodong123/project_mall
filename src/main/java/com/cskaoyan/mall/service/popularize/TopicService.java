package com.cskaoyan.mall.service.popularize;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.bean.TopicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicService {
    List<Topic> selectByConditions(@Param("title") String title,
                                   @Param("subtitle") String subtitle,
                                   @Param("sort") String sort,
                                   @Param("order") String order);

    long countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExampleWithBLOBs(TopicExample example);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer id);

    Topic selectByTitle(@Param("title") String title);

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExampleWithBLOBs(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    List queryGoodsById(int id);

    List<Topic> queryTopicRelated(int id);
}

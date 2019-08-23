package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.bean.TopicExample;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.service.popularize.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public List<Topic> selectByConditions(String title, String subtitle, String sort, String order) {
        return topicMapper.selectByConditions(title, subtitle, sort, order);
    }

    @Override
    public long countByExample(TopicExample example) {
        return topicMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TopicExample example) {
        return topicMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return topicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Topic record) {
        return topicMapper.insert(record);
    }

    @Override
    public int insertSelective(Topic record) {
        return topicMapper.insertSelective(record);
    }

    @Override
    public List<Topic> selectByExampleWithBLOBs(TopicExample example) {
        return topicMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Topic> selectByExample(TopicExample example) {
        return topicMapper.selectByExample(example);
    }

    @Override
    public Topic selectByPrimaryKey(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public Topic selectByTitle(String title) {
        return topicMapper.selectByTitle(title);
    }

    @Override
    public int updateByExampleSelective(Topic record, TopicExample example) {
        return topicMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExampleWithBLOBs(Topic record, TopicExample example) {
        return topicMapper.updateByExampleWithBLOBs(record, example);
    }

    @Override
    public int updateByExample(Topic record, TopicExample example) {
        return topicMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Topic record) {
        return topicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Topic record) {
        return topicMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Topic record) {
        return topicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List queryGoodsById(int id) {
        List list = topicMapper.queryGoodsById(id);
        return list;
    }

    @Override
    public List<Topic> queryTopicRelated(int id) {
        List<Topic> topics = topicMapper.selectTopicForFive();
        for(Topic topic : topics) {
            if(topic.getId() == id) {
                topics.remove(topic);
                break;
            }
        }
        return topics;
    }
}

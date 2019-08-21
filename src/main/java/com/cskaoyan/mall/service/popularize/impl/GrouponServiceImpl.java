package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponExample;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.service.popularize.GrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponMapper grouponMapper;

    @Override
    public long countByExample(GrouponExample example) {
        return grouponMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(GrouponExample example) {
        return grouponMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return grouponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Groupon record) {
        return grouponMapper.insert(record);
    }

    @Override
    public int insertSelective(Groupon record) {
        return grouponMapper.insertSelective(record);
    }

    @Override
    public List<Groupon> selectByExample(GrouponExample example) {
        return grouponMapper.selectByExample(example);
    }

    @Override
    public List<Groupon> selectByConditions(Integer goodsId, String sort, String order) {
        return grouponMapper.selectByConditions(goodsId, sort, order);
    }

    @Override
    public Groupon selectByPrimaryKey(Integer id) {
        return grouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Groupon record, GrouponExample example) {
        return grouponMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Groupon record, GrouponExample example) {
        return grouponMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Groupon record) {
        return grouponMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Groupon record) {
        return grouponMapper.updateByPrimaryKey(record);
    }
}

package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.GrouponRulesExample;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.popularize.GrouponRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Override
    public List<GrouponRules> selectByConditions(Integer goodsId, String sort, String order) {
        return grouponRulesMapper.selectByConditions(goodsId, sort, order);
    }

    @Override
    public long countByExample(GrouponRulesExample example) {
        return grouponRulesMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(GrouponRulesExample example) {
        return grouponRulesMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return grouponRulesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GrouponRules record) {
        return grouponRulesMapper.insert(record);
    }

    @Override
    public int insertSelective(GrouponRules record) {
        return grouponRulesMapper.insertSelective(record);
    }

    @Override
    public List<GrouponRules> selectByExample(GrouponRulesExample example) {
        return grouponRulesMapper.selectByExample(example);
    }

    @Override
    public GrouponRules selectByPrimaryKey(Integer id) {
        return grouponRulesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(GrouponRules record, GrouponRulesExample example) {
        return grouponRulesMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(GrouponRules record, GrouponRulesExample example) {
        return grouponRulesMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(GrouponRules record) {
        return grouponRulesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GrouponRules record) {
        return grouponRulesMapper.updateByPrimaryKey(record);
    }
}

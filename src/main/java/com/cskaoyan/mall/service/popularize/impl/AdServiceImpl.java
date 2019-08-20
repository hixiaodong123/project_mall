package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.AdExample;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.service.popularize.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;

    @Override
    public long countByExample(AdExample example) {
        return adMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AdExample example) {
        return adMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return adMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ad record) {
        return adMapper.insert(record);
    }

    @Override
    public int insertSelective(Ad record) {
        return adMapper.insertSelective(record);
    }

    @Override
    public List<Ad> selectByExample(AdExample example) {
        return adMapper.selectByExample(example);
    }

    @Override
    public List<Ad> selectAll(String name, String centent, String sort, String order) {
        return adMapper.selectAll(name, centent, sort, order);
    }


    @Override
    public Ad selectByPrimaryKey(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Ad record, AdExample example) {
        return adMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Ad record, AdExample example) {
        return adMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Ad record) {
        return adMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ad record) {
        return adMapper.updateByPrimaryKey(record);
    }
}

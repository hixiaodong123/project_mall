package com.cskaoyan.mall.service.person.collect.impl;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.CollectExample;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.service.person.collect.WxCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 收藏模块业务实现类
 * @author: Lime
 * @create: 2019-08-22 16:53
 **/

@Service
public class WxCollectServiceImpl implements WxCollectService
{
    private final CollectMapper collectMapper;

    @Autowired
    public WxCollectServiceImpl(CollectMapper collectMapper)
    {
        this.collectMapper = collectMapper;
    }

    @Override
    public List<Collect> findByType(Byte type, Integer userId)
    {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andTypeEqualTo(type).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return collectMapper.selectByExample(collectExample);
    }

    @Override
    public Collect getCollect(Integer userId, Byte type, Integer valueId)
    {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andValueIdEqualTo(valueId).andUserIdEqualTo(userId).andTypeEqualTo(type);
        List<Collect> collects = collectMapper.selectByExample(collectExample);

        if (collects.size() > 0)
        {
            return collects.get(0);
        }
        else
        {
            return null;
        }

    }

    @Override
    public void delete(Collect collect)
    {
        //CollectExample collectExample = new CollectExample();
        //collectExample.createCriteria().andValueIdEqualTo(collect.getValueId()).andUserIdEqualTo(collect.getValueId()).andTypeEqualTo(collect.getType());
        //List<Collect> collects = collectMapper.selectByExample(collectExample);
        //collect = collects.get(0);
        //collect.setDeleted(true);
        //collectMapper.updateByPrimaryKey(collect);

        collectMapper.updateDelete(collect.getValueId(),collect.getUserId(),collect.getType());
    }

    @Override
    public void addCollect(Collect collect)
    {
        collectMapper.insertSelective(collect);
    }
}

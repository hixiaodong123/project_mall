package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.service.user.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;

    @Override
    public List<Collect> listColletByCondition(String userId, String valueId) {
        List<Collect> users = collectMapper.listColletByCondition(userId,valueId);
        return users;
    }
}

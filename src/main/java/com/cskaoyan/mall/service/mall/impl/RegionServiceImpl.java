package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.service.mall.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<Region> queryRegionList() {
        List<Region> regions = regionMapper.queryRegionList();
        return regions;
    }
}
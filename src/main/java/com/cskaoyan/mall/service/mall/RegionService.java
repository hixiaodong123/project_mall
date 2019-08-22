package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionService {
    List<Region> queryRegionList();

    List<Region> selectRegionByPid(@Param("pid")Integer pid);
}

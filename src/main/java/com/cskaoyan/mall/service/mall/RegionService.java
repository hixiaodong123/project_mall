package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionService {
    List<Region> queryRegionList();

    String queryReginNameById(int id);

    List<Region> queryReginListByPid(int pid);

    List<Region> selectRegionByPid(@Param("pid")Integer pid);
}

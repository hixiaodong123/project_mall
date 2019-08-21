package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Api;
import com.cskaoyan.mall.bean.ApiExample;
import com.cskaoyan.mall.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiMapper apiMapper;

    @Override
    public boolean insertApi(Api api) {
        return 0 != apiMapper.insert(api);
    }

    @Override
    public boolean deleteApiByAutId(int autId) {
        return false;
    }

    @Override
    public boolean updateApi(Api api) {
        return false;
    }

    @Override
    public List<Api> listApis() {
        return apiMapper.selectByExample(new ApiExample());
    }

    @Override
    public List<Api> listApisByType(byte type) {
        return null;
    }

    @Override
    public Api listApisById(String id) {
        ApiExample apiExample = new ApiExample();
        apiExample.createCriteria().andIdEqualTo(id);
        List<Api> apis = apiMapper.selectByExample(apiExample);
        if (apis.size() == 0) return null;
        return apis.get(0);
    }

    @Override
    public List<Map> listApisBySort(String sort) {
        return null;
    }
}

package com.cskaoyan.mall.service.admin;



import com.cskaoyan.mall.bean.Api;

import java.util.List;
import java.util.Map;

public interface ApiService {

    boolean insertApi(Api api);

    boolean deleteApiByAutId(int autId);

    boolean updateApi(Api api);

    List<Api> listApis();

    List<Api> listApisByType(byte type);

    Api listApisById(String id);

    List<Map> listApisBySort(String sort);
}

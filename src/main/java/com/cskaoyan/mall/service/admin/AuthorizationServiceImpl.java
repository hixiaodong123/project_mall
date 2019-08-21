package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Api;
import com.cskaoyan.mall.bean.ApiExample;
import com.cskaoyan.mall.bean.Authorization;
import com.cskaoyan.mall.bean.AuthorizationExample;
import com.cskaoyan.mall.mapper.AuthorizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    AuthorizationMapper authorizationMapper;

    @Autowired
    ApiService apiService;

    @Override
    public boolean insertAuthorization(Authorization authorization) {
        return 0 != authorizationMapper.insert(authorization);
    }

    @Override
    public boolean deleteAuthorizationByAutId(int autId) {
        return false;
    }

    @Override
    public boolean updateAuthorization(Authorization authorization) {
        return false;
    }

    @Override
    public List<Authorization> listAuthorizations() {
        return null;
    }

    @Override
    public List<Authorization> listAuthorizationsByType(byte type) {
        AuthorizationExample authorizationExample = new AuthorizationExample();
        authorizationExample.createCriteria().andTypeEqualTo(type);
        return authorizationMapper.selectByExample(authorizationExample);
    }

    @Override
    public Authorization listAuthorizationsById(String id) {
        AuthorizationExample authorizationExample = new AuthorizationExample();
        authorizationExample.createCriteria().andIdEqualTo(id);
        List<Authorization> authorizations = authorizationMapper.selectByExample(authorizationExample);
        if (authorizations == null || authorizations.size() == 0) return null;
        return authorizations.get(0);
    }

    @Override
    public Authorization listAuthorizationsByIdAndType(String id, byte type) {
        AuthorizationExample authorizationExample = new AuthorizationExample();
        authorizationExample.createCriteria().andIdEqualTo(id).andTypeEqualTo(type);
        List<Authorization> authorizations = authorizationMapper.selectByExample(authorizationExample);
        if (authorizations == null || authorizations.size() == 0) return null;
        return authorizations.get(0);
    }

    @Override
    public List<Map> listAuthorizationsBySort(String sort) {
        return null;
    }

    @Override
    public List<Map<String, Object>> permissionInfo() {

        AuthorizationExample authorizationExample1 = new AuthorizationExample();
        authorizationExample1.createCriteria().andTypeEqualTo((byte) 1);
        List<Authorization> autList1 = authorizationMapper.selectByExample(authorizationExample1);
        List<Map<String, Object>> systemPermissions = new ArrayList<>();
        for (Authorization au1 : autList1) {
            AuthorizationExample authorizationExample2 = new AuthorizationExample();
            authorizationExample2.createCriteria().andTypeEqualTo((byte) 2).andPidEqualTo(au1.getAutid());
            List<Authorization> autList2 = authorizationMapper.selectByExample(authorizationExample2);
            Map<String, Object> map1 = new HashMap<>();
            List<Map<String, Object>> children1 = new ArrayList<>();
            for (Authorization au2 : autList2) {
                AuthorizationExample authorizationExample3 = new AuthorizationExample();
                authorizationExample3.createCriteria().andTypeEqualTo((byte) 3).andPidEqualTo(au2.getAutid());
                List<Authorization> autList3 = authorizationMapper.selectByExample(authorizationExample3);
                Map<String, Object> map2 = new HashMap<>();
                List<Map<String, Object>> children2 = new ArrayList<>();
                for (Authorization au3 : autList3) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("id", au3.getId());
                    map3.put("label", au3.getLabel());
                    Api api = apiService.listApisById(au3.getId());
                    map3.put("api", api.getApi());
                    children2.add(map3);
                }
                map2.put("children", children2);
                map2.put("id", au2.getId());
                map2.put("label", au2.getLabel());
                children1.add(map2);
            }
            map1.put("children", children1);
            map1.put("id", au1.getId());
            map1.put("label", au1.getLabel());
            systemPermissions.add(map1);
        }
        return systemPermissions;
    }

    @Override
    public List<String> listAllPermission() {
        List<Api> apis = apiService.listApis();
        ArrayList<String> list = new ArrayList<>();
        for (Api api : apis) {
            list.add(api.getId());
        }
        return list;
    }
}

package com.cskaoyan.mall.service.admin;



import com.cskaoyan.mall.bean.Authorization;

import java.util.List;
import java.util.Map;

public interface AuthorizationService {

    boolean insertAuthorization(Authorization authorization);

    boolean deleteAuthorizationByAutId(int autId);

    boolean updateAuthorization(Authorization authorization);

    List<Authorization> listAuthorizations();

    List<Authorization> listAuthorizationsByType(byte type);

    Authorization listAuthorizationsById(String id);

    Authorization listAuthorizationsByIdAndType(String id, byte type);

    List<Map> listAuthorizationsBySort(String sort);

    List<Map<String, Object>> permissionInfo();

    List<String> listAllPermission();
}

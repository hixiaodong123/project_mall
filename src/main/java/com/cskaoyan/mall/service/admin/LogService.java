package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Log;

import java.util.List;

public interface LogService {

    boolean insertLog(Log log);

    boolean deleteLogById(int id);
    
    boolean updateLog(Log log);

    List<Log> listLogs();

    List<Log> listLogsBySort(String sort);

    Log listLogById(int id);

    List<Log> listLogsByLikeName(String name, String sort);

}

package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.LogExample;
import com.cskaoyan.mall.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogServiceImpl implements LogService {


    @Autowired
    LogMapper logMapper;

    @Override
    public boolean insertLog(Log log) {
        return 0 != logMapper.insert(log);
    }

    @Override
    public boolean deleteLogById(int id) {
        Log log = logMapper.selectByPrimaryKey(id);
        log.setDeleted(true);
        return 0 != logMapper.updateByPrimaryKey(log);
    }

    @Override
    public boolean updateLog(Log log) {
        return 0 != logMapper.updateByPrimaryKey(log);
    }

    @Override
    public List<Log> listLogs() {
        String sort = "add_time desc";
        return listLogsBySort(sort);
    }

    @Override
    public List<Log> listLogsBySort(String sort) {
        LogExample logExample = new LogExample();
        logExample.setOrderByClause(sort);
        return logMapper.selectByExample(logExample);
    }

    @Override
    public Log listLogById(int id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Log> listLogsByLikeName(String name, String sort) {
        if (name == null || "".equals(name)) return listLogsBySort(sort);
        LogExample logExample = new LogExample();
        name = "%" + name + "%";
        logExample.createCriteria().andAdminLike(name);
        return logMapper.selectByExample(logExample);
    }

}

package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageExample;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.popularize.AdStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdStorageServiceImpl implements AdStorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public long countByExample(StorageExample example) {
        return storageMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(StorageExample example) {
        return storageMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return storageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Storage record) {
        return storageMapper.insert(record);
    }

    @Override
    public int insertSelective(Storage record) {
        return storageMapper.insertSelective(record);
    }

    @Override
    public List<Storage> selectByExample(StorageExample example) {
        return storageMapper.selectByExample(example);
    }

    @Override
    public Storage selectByPrimaryKey(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Storage record, StorageExample example) {
        return storageMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Storage record, StorageExample example) {
        return storageMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Storage record) {
        return storageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Storage record) {
        return storageMapper.updateByPrimaryKey(record);
    }
}

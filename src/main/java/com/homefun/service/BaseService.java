/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by liuzh on 2014/12/11.
 */
@Service
public abstract class BaseService<T> implements IService<T> {
    @Autowired
    protected Mapper<T> mapper;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public T selectByKey(Object key) {

        return mapper.selectByPrimaryKey(key);
    }
    @CacheEvict(value="myCache",allEntries=true,beforeInvocation=true)
    public int save(T entity) {
        return mapper.insert(entity);
    }
    @CacheEvict(value="myCache",allEntries=true,beforeInvocation=true)
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }
    @CacheEvict(value="myCache",allEntries=true,beforeInvocation=true)
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }
    @CacheEvict(value="myCache",allEntries=true,beforeInvocation=true)
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }


}

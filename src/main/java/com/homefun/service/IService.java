/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);
    public List<T> selectAll();
    //TODO 其他...
}
